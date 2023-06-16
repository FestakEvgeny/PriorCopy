package fetskovich.evgeny.components.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme

@Composable
fun BaseBottomNavigation(
    tabs: List<BottomNavigationTabItem>,
    currentRoute: String,
    onItemClicked: (tab: BottomNavigationTabItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = ApplicationTheme.colors.background,
        modifier = modifier
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = {
                    tab.icon?.let {
                        Icon(
                            painter = tab.icon,
                            contentDescription = tab.title
                        )
                    }
                },
                label = {
                    Text(
                        text = tab.title
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = ApplicationTheme.colors.baseTextColor,
                unselectedContentColor = ApplicationTheme.colors.baseTextColor.copy(
                    alpha = 0.4f,
                ),
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
                    icon = null,
                    route = "/Home"
                ),
                BottomNavigationTabItem(
                    title = "Settings",
                    icon = null,
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
