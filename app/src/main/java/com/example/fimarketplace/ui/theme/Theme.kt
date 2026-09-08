package com.example.fimarketplace.ui.theme

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

// =====================================
// 1Fi MARKETPLACE COLORS
// =====================================

val FiPurple = Color(0xFF6C4AB6)
val FiPurpleDark = Color(0xFF5B3A9E)
val FiPurpleLight = Color(0xFFF0EAFB)

val FiBackground = Color(0xFFF8F7FC)
val FiSurface = Color(0xFFFFFFFF)
val FiImageBackground = Color(0xFFF1EFF7)

val FiTextPrimary = Color(0xFF1C1B1F)
val FiTextSecondary = Color(0xFF6F6B76)


// =====================================
// DARK COLOR SCHEME
// =====================================

private val DarkColorScheme = darkColorScheme(
    primary = FiPurple,
    secondary = FiPurpleDark,
    tertiary = FiPurpleLight
)


// =====================================
// LIGHT COLOR SCHEME
// =====================================

private val LightColorScheme = lightColorScheme(
    primary = FiPurple,
    secondary = FiPurpleDark,
    tertiary = FiPurpleLight,

    background = FiBackground,
    surface = FiSurface,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = FiTextPrimary,
    onSurface = FiTextPrimary
)


// =====================================
// THEME
// =====================================

@Composable
fun FiMarketplaceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    // Dynamic colors OFF
    // so that our 1Fi-style colors remain consistent
    dynamicColor: Boolean = false,

    content: @Composable () -> Unit
) {

    val colorScheme = when {

        dynamicColor &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {

            val context = LocalContext.current

            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> {
            DarkColorScheme
        }

        else -> {
            LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}