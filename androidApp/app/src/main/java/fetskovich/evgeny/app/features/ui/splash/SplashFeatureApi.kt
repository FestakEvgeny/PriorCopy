package fetskovich.evgeny.app.features.ui.splash

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.navigation.graph.splash.SplashScreenNavigation

class SplashFeatureApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = SplashScreenNavigation.route
        ) {
            SplashScreen(
                navController = navController,
            )
        }
    }
}