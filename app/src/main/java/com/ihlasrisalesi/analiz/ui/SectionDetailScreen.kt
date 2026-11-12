package com.ihlasrisalesi.analiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihlasrisalesi.analiz.data.GlossaryHelper
import com.ihlasrisalesi.analiz.data.GlossaryItem
import com.ihlasrisalesi.analiz.data.SectionItem
import com.ihlasrisalesi.analiz.ui.theme.EmeraldPrimary
import com.ihlasrisalesi.analiz.ui.theme.GoldAccent
import com.ihlasrisalesi.analiz.ui.theme.GoldBright

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionDetailScreen(
    section: SectionItem,
    onBackClick: () -> Unit,
    onListenSectionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    var fontSizeSp by remember { mutableStateOf(16f) }
    var userNote by remember { mutableStateOf("") }
    var showNoteSavedMsg by remember { mutableStateOf(false) }

    // Lügatçe BottomSheet durumu
    var selectedGlossaryItem by remember { mutableStateOf<GlossaryItem?>(null) }
    var selectedGlossaryWord by remember { mutableStateOf<String?>(null) }
    var showGlossarySheet by remember { mutableStateOf(false) }

    val tabs = listOf("Konu Analizi", "Orijinal Metin", "Dersler & İlkeler", "Tefekkür")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = section.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        fontSizeSp = if (fontSizeSp >= 22f) 14f else fontSizeSp + 2f
                    }) {
                        Icon(Icons.Default.FormatSize, contentDescription = "Yazı Boyutu", tint = GoldAccent)
                    }
                    IconButton(onClick = { onListenSectionClick(section.id) }) {
                        Icon(Icons.Default.Headphones, contentDescription = "Sesli Takip Et", tint = GoldAccent)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 16.dp,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = EmeraldPrimary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTab == index) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldPrimary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = GoldAccent.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = section.category.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldBright,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = section.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = section.subtitle,
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        FilledTonalButton(
                            onClick = { onListenSectionClick(section.id) },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = GoldAccent,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(Icons.Default.PlayCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Bu Konuyu Başlat (Giriş Olmadan)", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (selectedTab) {
                    0 -> ModernAnalysisView(section = section, fontSizeSp = fontSizeSp)
                    1 -> OriginalTextView(
                        section = section,
                        fontSizeSp = fontSizeSp,
                        onWordClick = { clickedWord, wordIdx, wordsList ->
                            val clean = clickedWord.trim { !it.isLetterOrDigit() && it != '-' }
                            val foundItem = GlossaryHelper.findGlossaryItem(clean, wordsList, wordIdx)
                            selectedGlossaryItem = foundItem
                            selectedGlossaryWord = clean
                            showGlossarySheet = true
                        }
                    )
                    2 -> LessonsView(section = section)
                    3 -> ReflectionView(
                        section = section,
                        noteText = userNote,
                        onNoteChange = { userNote = it },
                        showSavedMsg = showNoteSavedMsg,
                        onSaveNote = { showNoteSavedMsg = true }
                    )
                }
            }

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
                    }
                )
            }
        }
    }
}

@Composable
fun ModernAnalysisView(section: SectionItem, fontSizeSp: Float) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, tint = EmeraldPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Geniş Konu Açıklaması", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = section.modernExplanation,
                fontSize = fontSizeSp.sp,
                lineHeight = (fontSizeSp * 1.55f).sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun OriginalTextView(
    section: SectionItem,
    fontSizeSp: Float,
    onWordClick: (String, Int, List<String>) -> Unit
) {
    val words = remember(section.originalText) { section.originalText.split(Regex("\\s+")).filter { it.isNotBlank() } }
    val baseTextColor = MaterialTheme.colorScheme.onSurface
    val isGlossaryFlags = remember(words) { words.map { GlossaryHelper.isGlossaryWord(it) } }

    val annotatedText = remember(section.originalText, baseTextColor, words, isGlossaryFlags) {
        buildAnnotatedString {
            words.forEachIndexed { idx, word ->
                val isGlossary = isGlossaryFlags[idx]
                pushStringAnnotation(tag = "WORD_IDX", annotation = idx.toString())

                val color = if (isGlossary) Color(0xFF81D4FA) else baseTextColor
                val weight = if (isGlossary) FontWeight.Bold else FontWeight.Normal
                val decoration = if (isGlossary) TextDecoration.Underline else TextDecoration.None

                withStyle(SpanStyle(color = color, fontWeight = weight, textDecoration = decoration)) {
                    append(word)
                }
                pop()
                append(" ")
            }
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FormatQuote, contentDescription = null, tint = GoldAccent)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Risale-i Nur Orijinal Metni", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
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
            Spacer(modifier = Modifier.height(12.dp))
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
                    }
                }
            )
        }
    }
}

@Composable
fun LessonsView(section: SectionItem) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        section.keyLessons.forEachIndexed { index, lesson ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(EmeraldPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = GoldBright,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = lesson,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun ReflectionView(
    section: SectionItem,
    noteText: String,
    onNoteChange: (String) -> Unit,
    showSavedMsg: Boolean,
    onSaveNote: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Nefis Muhasebesi ve Sorular",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = GoldAccent
                )
                Spacer(modifier = Modifier.height(8.dp))
                section.reflectionQuestions.forEach { q ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.QuestionAnswer,
                            contentDescription = null,
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = q,
                            fontSize = 14.sp,
                            lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Kişisel Tefekkür Notum",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = noteText,
                    onValueChange = onNoteChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Bu düsturdan aldığınız dersi ve hislerinizi not edin...") },
                    minLines = 3,
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onSaveNote,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
                ) {
                    Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Notu Kaydet")
                }
                if (showSavedMsg) {
                    Text(
                        text = "✓ Notunuz başarıyla hafızaya kaydedildi.",
                        color = EmeraldPrimary,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
