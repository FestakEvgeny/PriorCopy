@file:Suppress("MatchingDeclarationName")
package fetskovich.evgeny.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import fetskovich.evgeny.theme.colors.AppColorPalette
import fetskovich.evgeny.theme.colors.AppColors
import fetskovich.evgeny.theme.colors.LocalAppColors
import fetskovich.evgeny.theme.colors.appColors
import fetskovich.evgeny.theme.shapes.shapes
import fetskovich.evgeny.theme.typography.typography

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
