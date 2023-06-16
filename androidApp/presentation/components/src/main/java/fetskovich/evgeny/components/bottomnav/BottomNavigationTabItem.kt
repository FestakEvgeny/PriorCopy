package fetskovich.evgeny.components.bottomnav

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationTabItem(
  val title: String,
  val icon: Painter?,
  val route: String
)
