package fetskovich.evgeny.app.features.ui.unauthorized.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
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
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.app.features.ui.unauthorized.assistance.api.AssistanceScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.cashpoints.api.CashpointsScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.api.ExchangesScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.login.api.LoginScreenNavigation
import fetskovich.evgeny.app.features.ui.unauthorized.more.api.UnauthorizedMoreScreensGraphNavigation
import fetskovich.evgeny.components.bottomnav.BaseBottomNavigation
import fetskovich.evgeny.components.bottomnav.BottomNavigationTabItem
import fetskovich.evgeny.recipeskmm.app.R

// TODO Combine with AuthorizedMainScreen in terms of reusability
@Composable
fun UnauthorizedMainScreen (
    parentNavController: NavHostController,
    navController: NavHostController,
    features: Set<FeatureApi>,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: LoginScreenNavigation.route

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
        startDestination = LoginScreenNavigation.route,
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
        title = stringResource(id = R.string.unauthorized_bottom_tab_login),
        icon = painterResource(id = R.drawable.ic_login),
        route = LoginScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.unauthorized_bottom_tab_cashiers),
        icon = painterResource(id = R.drawable.ic_location),
        route = CashpointsScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.unauthorized_bottom_tab_exchanges),
        icon = painterResource(id = R.drawable.ic_location),
        route = ExchangesScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.unauthorized_bottom_tab_assistance),
        icon = painterResource(id = R.drawable.ic_location),
        route = AssistanceScreenNavigation.route
    ),
    BottomNavigationTabItem(
        title = stringResource(id = R.string.unauthorized_bottom_tab_more),
        icon = painterResource(id = R.drawable.ic_location),
        route = UnauthorizedMoreScreensGraphNavigation.route
    ),
)