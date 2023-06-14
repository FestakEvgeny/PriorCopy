package fetskovich.evgeny.components.bottomnav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.theme.ApplicationTheme
import fetskovich.evgeny.theme.BasicTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseBottomNavigation(
  tabs: List<BottomNavigationTabItem>,
  currentRoute: String,
  onItemClicked: (tab: BottomNavigationTabItem) -> Unit,
  modifier: Modifier = Modifier
) {
  BottomNavigation(
    modifier = modifier
  ) {
    tabs.forEach { tab ->
      BottomNavigationItem(
        icon = {
          Icon(
            imageVector = tab.icon,
            contentDescription = tab.title
          )
        },
        label = {
          Text(
            text = tab.title
          )
        },
        alwaysShowLabel = true,
        selectedContentColor = ApplicationTheme.colors.iconColor,
        unselectedContentColor = ApplicationTheme.colors.secondary,
        selected = currentRoute.contains(tab.route),
        onClick = {
          onItemClicked(tab)
        }
      )
    }
  }
}

@Preview
@Composable
private fun BaseBottomNavigationPreview() {
  BasicTheme {
    BaseBottomNavigation(
      tabs = listOf(
        BottomNavigationTabItem(
          title = "Home",
          icon = Filled.Home,
          route = "/Home"
        ),
        BottomNavigationTabItem(
          title = "Settings",
          icon = Filled.Settings,
          route = "/Settings"
        )
      ),
      currentRoute = "/Home",
      onItemClicked = {},
      modifier = Modifier
        .fillMaxWidth()
    )
  }
}
