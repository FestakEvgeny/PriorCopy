package fetskovich.evgeny.app.features.ui.main.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.components.bottomnav.BaseBottomNavigation
import fetskovich.evgeny.components.bottomnav.BottomNavigationTabItem
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph
import fetskovich.evgeny.navigation.graph.settings.SettingsRootNavGraph
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun BottomNavigationScreen(
    parentNavController: NavHostController,
    navController: NavHostController,
    features: Set<FeatureApi>,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: OverviewRootNavGraph.route

    Scaffold(
        bottomBar = {
            BaseBottomNavigation(
                tabs = createRoutes(),
                currentRoute = currentRoute,
                onItemClicked = { tab ->
                    navController.navigate(tab.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { paddings ->
        BottomNavigationNavHost(
            mainNavController = parentNavController,
            bottomNavController = navController,
            features = features,
            modifier = Modifier
                .padding(paddings)
        )
    }
}

@Composable
private fun createRoutes() = listOf(
    BottomNavigationTabItem(
        title = stringResource(id = R.string.overview_tab),
        icon = Icons.Filled.Home,
        route = OverviewRootNavGraph.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.settings_tab),
        icon = Icons.Filled.Settings,
        route = SettingsRootNavGraph.route
    ),
)