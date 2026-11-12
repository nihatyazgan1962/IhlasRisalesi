package com.ihlasrisalesi.analiz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val EmeraldPrimary = Color(0xFF144D37)
val EmeraldLight = Color(0xFF1E6B4E)
val GoldAccent = Color(0xFFD4A346)
val GoldBright = Color(0xFFF5C564)
val GoldLight = Color(0xFFFFF0D0)

val DarkBackground = Color(0xFF0F1713)
val DarkSurface = Color(0xFF16231D)
val DarkSurfaceVariant = Color(0xFF1F3028)
val DarkOnBackground = Color(0xFFE4ECE8)

val LightBackground = Color(0xFFF7FAF8)
val LightSurface = Color(0xFFFFFFFF)
val LightSurfaceVariant = Color(0xFFEDF3F0)
val LightOnBackground = Color(0xFF14241D)

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = Color(0xFF1A1405),
    primaryContainer = EmeraldPrimary,
    onPrimaryContainer = GoldBright,
    secondary = GoldBright,
    onSecondary = Color(0xFF1E1600),
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnBackground,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFBDCCC4)
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4EBDD),
    onPrimaryContainer = Color(0xFF072418),
    secondary = GoldAccent,
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnBackground,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = Color(0xFF40534A)
)

@Composable
fun IhlasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

