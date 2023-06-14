package fetskovich.evgeny.theme.colors

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

data class AppColors(
  val materialColors: Colors,
  val toolbarItemColor: Color,
  val iconColor: Color,
) {
  val primary = materialColors.primary
  val primaryVariant = materialColors.primaryVariant
  val secondary = materialColors.secondary
  val secondaryVariant = materialColors.secondaryVariant
  val background = materialColors.background
  val surface = materialColors.surface
  val error = materialColors.error
  val onPrimary = materialColors.onPrimary
  val onSecondary = materialColors.onSecondary
  val onBackground = materialColors.onBackground
  val onSurface = materialColors.onSurface
  val onError = materialColors.onError
  val isLight = materialColors.isLight
}
