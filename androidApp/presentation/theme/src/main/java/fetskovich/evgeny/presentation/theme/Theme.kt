@file:Suppress("MatchingDeclarationName")
package fetskovich.evgeny.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import fetskovich.evgeny.presentation.theme.colors.AppColorPalette
import fetskovich.evgeny.presentation.theme.colors.AppColors
import fetskovich.evgeny.presentation.theme.colors.LocalAppColors
import fetskovich.evgeny.presentation.theme.colors.appColors
import fetskovich.evgeny.presentation.theme.shapes.shapes
import fetskovich.evgeny.presentation.theme.typography.typography

object ApplicationTheme {
  val colors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColors.current
}

@Composable
fun BasicTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  palette: AppColorPalette = AppColorPalette.YELLOW,
  content: @Composable () -> Unit
) {
  val colors = appColors(palette = palette, darkTheme = darkTheme)

  CompositionLocalProvider(
    LocalAppColors provides colors
  ) {
    MaterialTheme(
      colors = colors.materialColors,
      typography = typography,
      shapes = shapes,
      content = content
    )
  }
}

@Composable
private fun BasicThemePreview() {

}