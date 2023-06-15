package fetskovich.evgeny.presentation.theme.colors.types

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import fetskovich.evgeny.presentation.theme.colors.AppColors

internal fun yellowAppColors(isDark: Boolean) = if (isDark) {
  yellowDarkColorPalette
} else {
  yellowLightColorPalette
}

@SuppressLint("ConflictingOnColor")
private val yellowDarkColorPalette = AppColors(
  toolbarItemColor = Color(0xFFFFFFFF),
  iconColor = Color(0xFFFFFFFF),
  baseTextColor = Color(0xFF424242),
  materialColors = darkColors(
    primary = Color(0xFF795CD1),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF6200EE),
    secondary = Color(0xFF6200EE),
    secondaryVariant = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    error = Color(0xFFCC0000),
    onError = Color(0xFFFFFFFF),
  )
)

@SuppressLint("ConflictingOnColor")
private val yellowLightColorPalette = AppColors(
  toolbarItemColor = Color(0xFFFFFFFF),
  iconColor = Color(0xFFFFFFFF),
  baseTextColor = Color(0xFF424242),
  materialColors = lightColors(
    primary = Color(0xFFFAEB38),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF6200EE),
    secondary = Color(0xFF28025E),
    secondaryVariant = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    error = Color(0xFFCC0000),
  )
)
