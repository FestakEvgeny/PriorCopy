package fetskovich.evgeny.presentation.theme.colors.types

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
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
  activeLink = Color(0xFF396BAD),
  materialColors = darkColors(
    primary = Color(0xFFFAEB38),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF202020),
    secondary = Color(0xFF424242),
    secondaryVariant = Color(0xFF7C7A7A),
    surface = Color(0xFCFFFFFF),
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
  activeLink = Color(0xFF396BAD),
  materialColors = darkColors(
    primary = Color(0xFFFAEB38),
    onPrimary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFF202020),
    secondary = Color(0xFFDADADA),
    secondaryVariant = Color(0xFF7C7A7A),
    surface = Color(0xFCFFFFFF),
    onSurface = Color(0xFF000000),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    error = Color(0xFFCC0000),
    onError = Color(0xFFFFFFFF),
  )
)
