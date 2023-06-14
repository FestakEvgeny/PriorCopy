package fetskovich.evgeny.components.bottomnav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationTabItem(
  val title: String,
  val icon: ImageVector,
  val route: String
)
