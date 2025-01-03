package com.sep.byteClub.ui.designSystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val coldForecast = Color(0xFF01A9D3)
val hotForecast = Color(0xFFC43D3D)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF24E5AF),
    secondary = Color(0xFFFFD54F),
    primaryContainer = Color(0xFF5C5B5B),
    onPrimaryContainer = Color(0xFFEAF1F3),
    secondaryContainer = Color(0xFF003C35),
    onSecondaryContainer = Color(0xFFAFDBE9),
    background = Color(0xFF0C0C2C),
    surface = Color(0xFF301C3B),
    onPrimary = Color(0xFFE9E9E9),
    onSecondary = Color.White,
    tertiaryContainer = Color(0xFF301C3B),
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFFC5C5C5),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF24E5AF),
    secondary = Color(0xFFFFD54F),
    tertiary = Color(0xFF3F51B5),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFCCCCCC),
    onPrimary = Color(0xFF1D1D1D),
    onSecondary = Color(0xFF1D1D1D),
    onTertiary = Color(0xFF1D1D1D),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    primaryContainer = Color(0xFF8A9DA7),
    onPrimaryContainer = Color(0xFF1D323D),
    secondaryContainer = Color(0xFFA5D2F7),
    onSecondaryContainer = Color(0xFF02455C)
)

@Composable
fun ByteClubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}