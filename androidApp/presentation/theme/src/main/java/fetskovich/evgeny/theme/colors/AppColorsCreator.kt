package fetskovich.evgeny.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import fetskovich.evgeny.theme.colors.types.yellowAppColors

internal val LocalAppColors = staticCompositionLocalOf {
  defaultColors()
}

private fun defaultColors() = yellowAppColors(isDark = false)

@Composable
internal fun appColors(
  palette: AppColorPalette,
  darkTheme: Boolean
): AppColors = when (palette) {
  AppColorPalette.YELLOW -> yellowAppColors(darkTheme)
}
