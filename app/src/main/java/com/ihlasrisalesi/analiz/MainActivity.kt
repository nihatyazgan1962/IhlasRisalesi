package com.ihlasrisalesi.analiz

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihlasrisalesi.analiz.audio.AudioPlayerManager
import com.ihlasrisalesi.analiz.data.SectionItem
import com.ihlasrisalesi.analiz.ui.*
import com.ihlasrisalesi.analiz.ui.theme.EmeraldPrimary
import com.ihlasrisalesi.analiz.ui.theme.GoldAccent
import com.ihlasrisalesi.analiz.ui.theme.GoldBright
import com.ihlasrisalesi.analiz.ui.theme.IhlasTheme

enum class ScreenTab(val title: String, val icon: ImageVector) {
    ANALYSIS("Analiz", Icons.AutoMirrored.Filled.MenuBook),
    AUDIO("Sesli Takip", Icons.Default.Headphones),
    GLOSSARY("Lügatçe", Icons.Default.AutoStories),
    TRACKER("15 Gün Takip", Icons.Default.Timer)
}

class MainActivity : ComponentActivity() {

    private lateinit var audioPlayerManager: AudioPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // TELEFON OKUNURKEN VE DİNLENİRKEN EKRANI KAPATMA:
        // FLAG_KEEP_SCREEN_ON sayesinde uygulama açıkken ve okunurken ekran asla kararmaz ve kapanmaz!
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        audioPlayerManager = AudioPlayerManager(applicationContext)

        setContent {
            IhlasTheme {
                MainApp(
                    audioPlayerManager = audioPlayerManager,
                    onKeepScreenOnChange = { keepAwake ->
                        if (keepAwake) {
                            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                        } else {
                            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        audioPlayerManager.release()
    }
}

@Composable
fun MainApp(
    audioPlayerManager: AudioPlayerManager,
    onKeepScreenOnChange: (Boolean) -> Unit = {}
) {
    var currentTab by remember { mutableStateOf(ScreenTab.ANALYSIS) }
    var selectedSection by remember { mutableStateOf<SectionItem?>(null) }
    var glossarySearchQuery by remember { mutableStateOf("") }
    val isPlaying by audioPlayerManager.isPlaying.collectAsState()
    val currentPosMs by audioPlayerManager.currentPositionMs.collectAsState()

    // Okunurken alt kontrol bölgesini gizleyerek metne tam ekran alan açma:
    var isAudioControlsVisible by remember { mutableStateOf(false) }

    // Ses çalmaya başladığında alt kontroller otomatik gizlensin (yazıları asla kapatmasın)
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            isAudioControlsVisible = false
        }
    }

    Scaffold(
        bottomBar = {
            Column {
                // Mini Player Bar (gösterilirken mini bar'a tıklanırsa doğrudan sesli ekrana geçer)
                AnimatedVisibility(
                    visible = isPlaying && currentTab != ScreenTab.AUDIO,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    MiniPlayer(
                        isPlaying = isPlaying,
                        currentPosMs = currentPosMs,
                        onPlayPause = { audioPlayerManager.togglePlayPause() },
                        onClick = { currentTab = ScreenTab.AUDIO }
                    )
                }

                // Alt Navigasyon Çubuğu: Sesli okuma ekranında ve kontroller gizliyken alt bar tamamen gizlenir!
                AnimatedVisibility(
                    visible = currentTab != ScreenTab.AUDIO || isAudioControlsVisible,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                ) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 8.dp
                    ) {
                        ScreenTab.values().forEach { tab ->
                            val isSelected = currentTab == tab && selectedSection == null
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    selectedSection = null
                                    currentTab = tab
                                },
                                icon = {
                                    Icon(
                                        imageVector = tab.icon,
                                        contentDescription = tab.title,
                                        tint = if (isSelected) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                label = {
                                    Text(
                                        text = tab.title,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = GoldAccent.copy(alpha = 0.25f)
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (selectedSection != null) {
                SectionDetailScreen(
                    section = selectedSection!!,
                    onBackClick = { selectedSection = null },
                    onListenSectionClick = { sectionId ->
                        selectedSection = null
                        currentTab = ScreenTab.AUDIO
                        audioPlayerManager.playSection(sectionId)
                    }
                )
            } else {
                when (currentTab) {
                    ScreenTab.ANALYSIS -> AnalysisListScreen(
                        onSectionClick = { section -> selectedSection = section },
                        onListenSectionClick = { sectionId ->
                            currentTab = ScreenTab.AUDIO
                            audioPlayerManager.playSection(sectionId)
                        }
                    )
                    ScreenTab.AUDIO -> AudioSyncedReaderScreen(
                        audioManager = audioPlayerManager,
                        isControlsVisible = isAudioControlsVisible,
                        onToggleControls = { isAudioControlsVisible = !isAudioControlsVisible },
                        onNavigateToGlossary = { query ->
                            glossarySearchQuery = query
                            currentTab = ScreenTab.GLOSSARY
                        }
                    )
                    ScreenTab.GLOSSARY -> GlossaryScreen(initialQuery = glossarySearchQuery)
                    ScreenTab.TRACKER -> FifteenDayTrackerScreen()
                }
            }
        }
    }
}

@Composable
fun MiniPlayer(
    isPlaying: Boolean,
    currentPosMs: Int,
    onPlayPause: () -> Unit,
    onClick: () -> Unit
) {
    Surface(
        color = EmeraldPrimary,
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = null,
                    tint = GoldBright,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "İhlas Risalesi Seslendiriliyor",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                    Text(
                        text = "${formatTime(currentPosMs)} • Kelime takibi için dokunun",
                        fontSize = 11.sp,
                        color = GoldBright
                    )
                }
            }

            IconButton(onClick = onPlayPause) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Duraklat" else "Çal",
                    tint = GoldBright,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
