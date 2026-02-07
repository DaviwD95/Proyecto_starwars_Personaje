package com.example.starwarspersonaje.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFD700), //amarillo
    background = Color(0xFF121212), //negro
    surface = Color(0xFF1C1C1C),
    onSurface = Color.White,
    onBackground = Color.White
)

private val ColorBlindColorScheme = darkColorScheme(
    primary = Color(0xFF00E5FF),   // cyan
    secondary = Color(0xFFFF9100),
    background = Color.Black,
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onBackground = Color.White
)



private val LightColorScheme = lightColorScheme(
    //primary = Purple40,
    //secondary = PurpleGrey40,
    //tertiary = Pink40
    primary = Color(0xFFFFD700),
    background = Color(0xFF121212),
    surface = Color(0xFF1C1C1C),
    onSurface = Color.White,
    onBackground = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun StarwarsPersonajeTheme(
    colorBlind: Boolean = false,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, //era true
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        colorBlind -> ColorBlindColorScheme
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}