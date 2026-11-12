package com.ihlasrisalesi.analiz.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihlasrisalesi.analiz.audio.AudioPlayerManager
import com.ihlasrisalesi.analiz.data.AudioSyncData
import com.ihlasrisalesi.analiz.data.GlossaryHelper
import com.ihlasrisalesi.analiz.data.GlossaryItem
import com.ihlasrisalesi.analiz.data.SyncedParagraph
import com.ihlasrisalesi.analiz.ui.theme.EmeraldPrimary
import com.ihlasrisalesi.analiz.ui.theme.GoldAccent
import com.ihlasrisalesi.analiz.ui.theme.GoldBright
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioSyncedReaderScreen(
    audioManager: AudioPlayerManager,
    isControlsVisible: Boolean = false,
    onToggleControls: () -> Unit = {},
    onNavigateToGlossary: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val isPlaying by audioManager.isPlaying.collectAsState()
    val currentPosMs by audioManager.currentPositionMs.collectAsState()
    val durationMs by audioManager.durationMs.collectAsState()
    val activeIndex by audioManager.activeParagraphIndex.collectAsState()
    val currentSpeed by audioManager.playbackSpeed.collectAsState()

    var isAutoScrollEnabled by remember { mutableStateOf(true) }
    var fontSizeSp by remember { mutableStateOf(17f) }
    var selectedTopicFilter by remember { mutableStateOf<String?>(null) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Lügatçe BottomSheet durumu:
    var selectedGlossaryItem by remember { mutableStateOf<GlossaryItem?>(null) }
    var selectedGlossaryWord by remember { mutableStateOf<String?>(null) }
    var showGlossarySheet by remember { mutableStateOf(false) }

    // OKUNAN YERİ SES AKIŞINA GÖRE YUKARI DOĞRU AKICI KAYDIR:
    LaunchedEffect(activeIndex, isPlaying, isAutoScrollEnabled) {
        if (!isAutoScrollEnabled || !isPlaying || activeIndex !in AudioSyncData.paragraphs.indices) return@LaunchedEffect
        val targetListIndex = activeIndex + 2

        // Yeni konuya geçildiğinde kartı yumuşakça ekranın başına getir:
        try {
            listState.animateScrollToItem(index = targetListIndex, scrollOffset = 0)
        } catch (_: Exception) {}

        // Seslendirme devam ettikçe okunan satırları takip ederek sayfayı yukarı kaydır:
        while (isActive && isPlaying && isAutoScrollEnabled) {
            delay(1000)
            if (!listState.isScrollInProgress) {
                val p = AudioSyncData.paragraphs.getOrNull(activeIndex) ?: break
                val duration = (p.endMs - p.startMs).coerceAtLeast(1)
                val progress = ((audioManager.currentPositionMs.value - p.startMs).toFloat() / duration).coerceIn(0f, 1f)

                val visibleItem = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == targetListIndex }
                val viewportHeight = listState.layoutInfo.viewportSize.height
                val itemHeight = visibleItem?.size ?: (p.originalText.length * 2.2f).toInt()

                // Okunan satır ekranın yaklaşık %28'ini geçtiğinde sayfa yukarı doğru akmaya başlar:
                val targetOffset = ((progress * itemHeight) - (viewportHeight * 0.28f))
                    .toInt()
                    .coerceIn(0, (itemHeight - 120).coerceAtLeast(0))

                try {
                    listState.animateScrollToItem(
                        index = targetListIndex,
                        scrollOffset = targetOffset
                    )
                } catch (_: Exception) {}
            }
        }
    }

    val quickTopics = listOf(
        "Tümü" to null,
        "İhtar" to 0,
        "Giriş" to 1,
        "1. Düstur" to 2,
        "2. Düstur" to 3,
        "Fabrika" to 4,
        "3. Düstur" to 5,
        "4. Düstur" to 6,
        "Çareler" to 7,
        "Mâniler" to 8,
        "Hubb-u Câh" to 9,
        "Hâtime" to 10
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "İhlas Risalesi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                actions = {
                    IconButton(onClick = onToggleControls) {
                        Icon(
                            imageVector = if (isControlsVisible) Icons.Default.VisibilityOff else Icons.Default.Tune,
                            contentDescription = if (isControlsVisible) "Kontrolleri Gizle" else "Kontrolleri Göster",
                            tint = GoldAccent
                        )
                    }
                    FilterChip(
                        selected = isAutoScrollEnabled,
                        onClick = { isAutoScrollEnabled = !isAutoScrollEnabled },
                        label = {
                            Text(
                                text = if (isAutoScrollEnabled) "Oto-Kaydır: Açık" else "Oto-Kaydır: Kapalı",
                                fontSize = 11.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = if (isAutoScrollEnabled) Icons.Default.SwapVert else Icons.Default.VerticalAlignBottom,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = if (isAutoScrollEnabled) GoldAccent else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = EmeraldPrimary.copy(alpha = 0.2f),
                            selectedLabelColor = GoldAccent
                        )
                    )
                    IconButton(onClick = {
                        fontSizeSp = if (fontSizeSp >= 22f) 15f else fontSizeSp + 2f
                    }) {
                        Icon(
                            imageVector = Icons.Default.FormatSize,
                            contentDescription = "Yazı Boyutu",
                            tint = GoldAccent
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isControlsVisible,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                AudioControlBar(
                    isPlaying = isPlaying,
                    currentPosMs = currentPosMs,
                    durationMs = durationMs,
                    currentSpeed = currentSpeed,
                    onTogglePlay = { audioManager.togglePlayPause() },
                    onSeek = { audioManager.seekTo(it) },
                    onRewind = { audioManager.rewind10() },
                    onForward = { audioManager.forward10() },
                    onSpeedChange = { audioManager.setSpeed(it) }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(
                    start = 14.dp,
                    end = 14.dp,
                    top = 6.dp,
                    bottom = if (isControlsVisible) 24.dp else 70.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Index 0: Hızlı Konular (Giriş/İhtar Tekrarsız Doğrudan Başlama)
                item {
                    Column {
                        Text(
                            text = "Konu Seçimi (Giriş Tekrarsız):",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(quickTopics) { (title, targetIndex) ->
                                val isSelected = (targetIndex == null && selectedTopicFilter == null) ||
                                        (targetIndex != null && activeIndex == targetIndex)
                                SuggestionChip(
                                    onClick = {
                                        if (targetIndex != null) {
                                            selectedTopicFilter = title
                                            audioManager.seekToParagraph(targetIndex)
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(
                                                    index = targetIndex + 2,
                                                    scrollOffset = 0
                                                )
                                            }
                                        } else {
                                            selectedTopicFilter = null
                                        }
                                    },
                                    label = {
                                        Text(
                                            text = title,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        containerColor = if (isSelected) EmeraldPrimary else MaterialTheme.colorScheme.surfaceVariant,
                                        labelColor = if (isSelected) GoldBright else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                        }
                    }
                }

                // Index 1: Bilgilendirme Banner'ı
                item {
                    ReadingBanner(
                        isPlaying = isPlaying,
                        onPlayClick = { audioManager.play() }
                    )
                }

                // Index 2 .. (N+2): Senkronize Paragraf Kartları
                itemsIndexed(AudioSyncData.paragraphs) { index, item ->
                    val isActive = index == activeIndex
                    SyncedParagraphCard(
                        item = item,
                        isActive = isActive,
                        isPlaying = isPlaying,
                        currentPosMs = currentPosMs,
                        fontSizeSp = fontSizeSp,
                        onWordClick = { clickedWord, wordIdx, wordsList ->
                            val clean = clickedWord.trim { !it.isLetterOrDigit() && it != '-' }
                            val foundItem = GlossaryHelper.findGlossaryItem(clean, wordsList, wordIdx)
                            selectedGlossaryItem = foundItem
                            selectedGlossaryWord = clean
                            showGlossarySheet = true
                        },
                        onClick = {
                            if (isActive) {
                                onToggleControls()
                            } else {
                                audioManager.seekToParagraph(index)
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = index + 2, scrollOffset = 0)
                                }
                            }
                        }
                    )
                }
            }

            // YÜZEN MİNİ KONTROL ÇUBUĞU (Yazıları ASLA kapatmayan minik şeffaf hap)
            AnimatedVisibility(
                visible = !isControlsVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            ) {
                Surface(
                    onClick = onToggleControls,
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.93f),
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp,
                    border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.6f))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        IconButton(
                            onClick = { audioManager.togglePlayPause() },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Duraklat" else "Oynat",
                                tint = GoldAccent,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${formatTime(currentPosMs)} / ${formatTime(durationMs)}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "•", color = GoldAccent)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = null,
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Kontroller",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldPrimary
                        )
                    }
                }
            }

            // KELİMEYE TIKLANDIĞINDA AÇILAN LÜGATÇE KARTI
            if (showGlossarySheet && selectedGlossaryWord != null) {
                GlossaryWordBottomSheet(
                    item = selectedGlossaryItem,
                    clickedWord = selectedGlossaryWord!!,
                    onDismiss = {
                        showGlossarySheet = false
                        selectedGlossaryWord = null
                        selectedGlossaryItem = null
                    },
                    onSearchInGlossary = { targetQuery ->
                        showGlossarySheet = false
                        onNavigateToGlossary(targetQuery)
                    }
                )
            }
        }
    }
}

@Composable
fun ReadingBanner(
    isPlaying: Boolean,
    onPlayClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Headphones,
                        contentDescription = null,
                        tint = GoldBright,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "İhsan Atasoy Seslendirmesi & Canlı Takip",
                        color = GoldBright,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (isPlaying)
                        "Seslendirme ilerledikçe sayfa okuma akışına göre yukarı doğru kayar."
                    else
                        "Dinlemek için başlatın veya dilediğiniz konuya dokunun.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 11.sp
                )
            }
            if (!isPlaying) {
                FilledIconButton(
                    onClick = onPlayClick,
                    modifier = Modifier.size(40.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = GoldAccent)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Başlat",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SyncedParagraphCard(
    item: SyncedParagraph,
    isActive: Boolean,
    isPlaying: Boolean,
    currentPosMs: Int,
    fontSizeSp: Float,
    onWordClick: (String, Int, List<String>) -> Unit,
    onClick: () -> Unit
) {
    var showAnalysis by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = if (isActive) GoldBright else Color.Transparent,
        animationSpec = tween(300),
        label = "borderColor"
    )

    val containerColor by animateColorAsState(
        targetValue = if (isActive) {
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
        } else {
            MaterialTheme.colorScheme.surface
        },
        animationSpec = tween(300),
        label = "containerColor"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isActive) 8.dp else 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isActive) 3.dp else 1.dp,
                color = if (isActive) borderColor else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Başlık ve Durum Rozeti Satırı
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (isActive) GoldAccent else MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${item.index + 1}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isActive) Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = if (isActive) GoldBright else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = item.subtitle,
                            fontSize = 12.sp,
                            color = if (isActive) GoldAccent else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isActive) EmeraldPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isActive && isPlaying) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = null,
                                tint = GoldBright,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Şu An Okunuyor",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldBright
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = formatTime(item.startMs),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isActive) GoldBright else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ASIL METİN BURADA: KELİME KELİME KESİN VE PARLAK GÖRÜNÜM + İNTERAKTİF LÜGATÇE
            KaraokeWordTextView(
                text = item.originalText,
                startMs = item.startMs,
                endMs = item.endMs,
                currentPosMs = currentPosMs,
                fontSizeSp = fontSizeSp,
                isActive = isActive,
                onWordClick = onWordClick,
                onBackgroundClick = onClick
            )

            // Lügat İpucu Notu
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 6.dp, bottom = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null,
                    tint = Color(0xFF81D4FA),
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Mavi renkli kelimelere dokunarak lügat anlamını görebilirsiniz.",
                    fontSize = 11.sp,
                    color = Color(0xFF81D4FA).copy(alpha = 0.9f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tahlil ve İzah Aç/Kapat Butonu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAnalysis = !showAnalysis },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (showAnalysis) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = GoldAccent,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (showAnalysis) "Tahlili Gizle" else "Konu Analizi & Tahlili Gör",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldAccent
                    )
                }
                Text(
                    text = item.keyThought,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }

            AnimatedVisibility(visible = showAnalysis) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = item.modernExplanation,
                            fontSize = (fontSizeSp - 2f).sp,
                            lineHeight = ((fontSizeSp - 2f) * 1.4f).sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

/**
 * Sayfa Metni ve İnteraktif Lügatçe Bileşeni:
 * - Konuşurken kelimelerin üstü boyanmaz; metin aslına uygun, gözü yormayan tertemiz bir akışla gösterilir.
 * - Lügatçede yer alan kelimeler belirgin ve ferah gök mavisi renkle vurgulanır, altı çizilir.
 * - Tıklanan kelimenin anında lügatçe izahı ve telaffuz bilgisi gösterilir.
 */
@Composable
fun KaraokeWordTextView(
    text: String,
    startMs: Int = 0,
    endMs: Int = 0,
    currentPosMs: Int = 0,
    fontSizeSp: Float,
    isActive: Boolean = false,
    onWordClick: (String, Int, List<String>) -> Unit,
    onBackgroundClick: () -> Unit
) {
    val words = remember(text) { text.split(Regex("\\s+")).filter { it.isNotBlank() } }
    val baseTextColor = MaterialTheme.colorScheme.onSurface
    val isGlossaryFlags = remember(words) { words.map { GlossaryHelper.isGlossaryWord(it) } }

    val annotatedText = remember(text, baseTextColor, words, isGlossaryFlags) {
        buildAnnotatedString {
            words.forEachIndexed { idx, word ->
                val isGlossary = isGlossaryFlags[idx]
                pushStringAnnotation(tag = "WORD_IDX", annotation = idx.toString())

                val color = if (isGlossary) Color(0xFF81D4FA) else baseTextColor
                val weight = if (isGlossary) FontWeight.SemiBold else FontWeight.Normal
                val decoration = if (isGlossary) TextDecoration.Underline else TextDecoration.None

                withStyle(
                    SpanStyle(
                        color = color,
                        fontWeight = weight,
                        textDecoration = decoration
                    )
                ) {
                    append(word)
                }
                pop()
                append(" ")
            }
        }
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            color = baseTextColor,
            fontSize = fontSizeSp.sp,
            lineHeight = (fontSizeSp * 1.6f).sp,
            fontFamily = FontFamily.Serif
        ),
        onClick = { offset ->
            val annotation = annotatedText.getStringAnnotations(tag = "WORD_IDX", start = offset, end = offset).firstOrNull()
            if (annotation != null) {
                val idx = annotation.item.toIntOrNull() ?: -1
                if (idx in words.indices) {
                    onWordClick(words[idx], idx, words)
                    return@ClickableText
                }
            }
            val word = getWordAtOffset(annotatedText.text, offset)
            if (word.isNotBlank()) {
                onWordClick(word, -1, words)
            } else {
                onBackgroundClick()
            }
        }
    )
}

fun getWordAtOffset(text: String, offset: Int): String {
    if (text.isEmpty() || offset !in text.indices) return ""
    var start = offset
    var end = offset
    while (start > 0 && !text[start - 1].isWhitespace()) {
        start--
    }
    while (end < text.length && !text[end].isWhitespace()) {
        end++
    }
    val raw = text.substring(start, end)
    return raw.trim { !it.isLetterOrDigit() && it != '-' }
}


@Composable
fun AudioControlBar(
    isPlaying: Boolean,
    currentPosMs: Int,
    durationMs: Int,
    currentSpeed: Float,
    onTogglePlay: () -> Unit,
    onSeek: (Int) -> Unit,
    onRewind: () -> Unit,
    onForward: () -> Unit,
    onSpeedChange: (Float) -> Unit
) {
    Surface(
        tonalElevation = 6.dp,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            // İnce, şık zaman kaydırıcısı
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatTime(currentPosMs),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Slider(
                    value = currentPosMs.toFloat().coerceIn(0f, durationMs.toFloat()),
                    onValueChange = { onSeek(it.toInt()) },
                    valueRange = 0f..durationMs.toFloat(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = GoldAccent,
                        activeTrackColor = EmeraldPrimary,
                        inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                Text(
                    text = formatTime(durationMs),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Kompakt butonlar satırı
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = {
                        val nextSpeed = when (currentSpeed) {
                            1.0f -> 1.25f
                            1.25f -> 1.5f
                            else -> 1.0f
                        }
                        onSpeedChange(nextSpeed)
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = "${currentSpeed}x",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = GoldAccent
                    )
                }

                IconButton(onClick = onRewind, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Default.Replay10,
                        contentDescription = "10sn Geri",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }

                FilledIconButton(
                    onClick = onTogglePlay,
                    modifier = Modifier.size(46.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = EmeraldPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Duraklat" else "Çal",
                        tint = GoldBright,
                        modifier = Modifier.size(26.dp)
                    )
                }

                IconButton(onClick = onForward, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Default.Forward10,
                        contentDescription = "10sn İleri",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(onClick = { onSeek(0) }, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Başa Dön",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

fun formatTime(ms: Int): String {
    val totalSeconds = (ms / 1000).coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
}
