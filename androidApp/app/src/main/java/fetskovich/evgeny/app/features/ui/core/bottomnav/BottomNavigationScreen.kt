package fetskovich.evgeny.app.features.ui.core.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.history.api.HistoryScreenNavigation
import fetskovich.evgeny.app.features.ui.core.main.api.MainScreensGraphNavigation
import fetskovich.evgeny.app.features.ui.core.more.api.MoreScreensGraphNavigation
import fetskovich.evgeny.app.features.ui.core.payments.api.PaymentsScreenNavigation
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.components.bottomnav.BaseBottomNavigation
import fetskovich.evgeny.components.bottomnav.BottomNavigationTabItem
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph
import fetskovich.evgeny.recipeskmm.app.R

@Composable
fun BottomNavigationScreen(
    parentNavController: NavHostController,
    navController: NavHostController,
    features: Set<FeatureApi>,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainScreensGraphNavigation.route

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
private fun BottomNavigationNavHost(
    mainNavController: NavHostController,
    bottomNavController: NavHostController,
    features: Set<FeatureApi>,
    modifier: Modifier
) {
    NavHost(
        navController = bottomNavController,
        startDestination = MainScreensGraphNavigation.route,
        modifier = modifier
    ) {
        features.forEach { feature ->
            register(
                feature = feature,
                navController = bottomNavController,
                parentNavController = mainNavController
            )
        }
    }
}

@Composable
private fun createRoutes() = listOf(
    BottomNavigationTabItem(
        title = stringResource(id = R.string.bottom_tab_main),
        icon = painterResource(id = R.drawable.ic_login),
        route = MainScreensGraphNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.bottom_tab_history),
        icon = painterResource(id = R.drawable.ic_location),
        route = HistoryScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.bottom_tab_payments),
        icon = painterResource(id = R.drawable.ic_assistant),
        route = PaymentsScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.bottom_tab_more),
        icon = painterResource(id = R.drawable.ic_more),
        route = MoreScreensGraphNavigation.route
    ),
)