package com.ihlasrisalesi.analiz.audio

import android.content.Context
import android.media.MediaPlayer
import android.media.PlaybackParams
import android.os.Build
import com.ihlasrisalesi.analiz.R
import com.ihlasrisalesi.analiz.data.AudioSyncData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AudioPlayerManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var tickerJob: Job? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0)
    val currentPositionMs: StateFlow<Int> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(1686880)
    val durationMs: StateFlow<Int> = _durationMs.asStateFlow()

    private val _activeParagraphIndex = MutableStateFlow(0)
    val activeParagraphIndex: StateFlow<Int> = _activeParagraphIndex.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    // İhsan Atasoy Ses Kaydı - Bölüm ID'lerine göre KESİN ses başlangıç milisaniyeleri
    // (Baştaki ihtar/mukaddime sadece giris_ihtar'da okunur, diğer konularda asla tekrar okunmaz!)
    private val sectionStartMs = mapOf(
        "giris_ihtar" to 0,
        "madem_ihlasta" to 76000,
        "birinci_dustur" to 210000,   // "BİRİNCİ DÜSTURUNUZ: Amelinizde rıza-yı İlâhî olmalı..."
        "ikinci_dustur" to 246000,    // "İKİNCİ DÜSTURUNUZ: Bu hizmet-i Kur'âniyede..."
        "fabrika_carklari" to 286000, // "Hem nasılki bir fabrikanın çarkları..."
        "ucuncu_dustur" to 476000,    // "ÜÇÜNCÜ DÜSTURUNUZ: Bütün kuvvetinizi ihlasta..."
        "dorduncu_dustur" to 650000,  // "DÖRDÜNCÜ DÜSTURUNUZ: Kardeşlerinizin meziyetlerini..."
        "ihlas_careleri" to 765000,   // "İHLASI KAZANDIRAN VE MUHAFAZA EDEN İKİ ÇARE..."
        "ihlas_manileri" to 941000,   // "İHLASI KIRAN VE RİYAYA SEVK EDEN MÂNİLER..."
        "hubb_u_cah" to 1280000,      // "İkinci Mâni: Hubb-u câh..."
        "hatime" to 1500000,          // "Bir kısım kardeşlerime hususi bir mektuptur..."
        "yirminci_lema" to 476000     // "Şahs-ı Manevî ve Uhuvvet Esası..."
    )

    init {
        initPlayer()
    }

    private fun initPlayer() {
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.ihlas_ses).apply {
                if (duration > 0) {
                    _durationMs.value = duration
                }
                setOnCompletionListener {
                    _isPlaying.value = false
                    stopTicker()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun togglePlayPause() {
        if (_isPlaying.value) {
            pause()
        } else {
            play()
        }
    }

    fun play() {
        if (mediaPlayer == null) {
            initPlayer()
        }
        mediaPlayer?.let { player ->
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    player.playbackParams = PlaybackParams().apply { speed = _playbackSpeed.value }
                }
                player.start()
                _isPlaying.value = true
                startTicker()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun pause() {
        mediaPlayer?.let { player ->
            try {
                if (player.isPlaying) {
                    player.pause()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _isPlaying.value = false
        stopTicker()
    }

    fun seekTo(positionMs: Int) {
        val safePos = positionMs.coerceIn(0, _durationMs.value)
        mediaPlayer?.seekTo(safePos)
        _currentPositionMs.value = safePos
        updateActiveParagraph(safePos)
    }

    fun seekToSeconds(seconds: Int) {
        seekTo(seconds * 1000)
    }

    /**
     * Seçilen konuyu doğrudan kendi başlangıç milisaniyesinden başlatır.
     * Baştaki ihtar/mukaddimeyi tekrarlamaz!
     */
    fun playSection(sectionId: String) {
        val startMs = sectionStartMs[sectionId] ?: 0
        seekTo(startMs)
        play()
    }

    fun seekToParagraph(index: Int) {
        val paragraph = AudioSyncData.paragraphs.getOrNull(index) ?: return
        seekTo(paragraph.startMs)
        if (!_isPlaying.value) {
            play()
        }
    }

    fun rewind10() {
        seekTo(_currentPositionMs.value - 10000)
    }

    fun forward10() {
        seekTo(_currentPositionMs.value + 10000)
    }

    fun setSpeed(speed: Float) {
        _playbackSpeed.value = speed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mediaPlayer != null && _isPlaying.value) {
            try {
                mediaPlayer?.playbackParams = PlaybackParams().apply { this.speed = speed }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun startTicker() {
        stopTicker()
        tickerJob = scope.launch {
            while (isActive) {
                mediaPlayer?.let { player ->
                    try {
                        if (player.isPlaying) {
                            val pos = player.currentPosition
                            _currentPositionMs.value = pos
                            updateActiveParagraph(pos)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                // Kelime kelime akıcı renklendirme için 60ms aralıkla güncellenir
                delay(60)
            }
        }
    }

    private fun stopTicker() {
        tickerJob?.cancel()
        tickerJob = null
    }

    private fun updateActiveParagraph(currentMs: Int) {
        val paragraphs = AudioSyncData.paragraphs
        val found = paragraphs.firstOrNull { currentMs >= it.startMs && currentMs < it.endMs }
        if (found != null && found.index != _activeParagraphIndex.value) {
            _activeParagraphIndex.value = found.index
        }
    }

    fun release() {
        stopTicker()
        try {
            mediaPlayer?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaPlayer = null
        _isPlaying.value = false
    }
}
