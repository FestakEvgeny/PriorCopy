package fetskovich.evgeny.app.features.ui.core.bottomnav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.main.api.MainScreensGraphNavigation
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph
import fetskovich.evgeny.navigation.graph.overview.RecipesNavigation

@Composable
fun BottomNavigationNavHost(
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