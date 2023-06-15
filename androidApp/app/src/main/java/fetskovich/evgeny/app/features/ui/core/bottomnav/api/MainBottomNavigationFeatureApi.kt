package fetskovich.evgeny.app.features.ui.core.bottomnav.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.bottomnav.BottomNavigationScreen

class MainBottomNavigationFeatureApi(
    private val features: Set<FeatureApi>
) : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = BottomScreenNavigation.route
        ) { _ ->
            val bottomNavController = rememberNavController()

            BottomNavigationScreen(
                parentNavController = navController,
                navController = bottomNavController,
                features = features,
            )
        }
    }
}