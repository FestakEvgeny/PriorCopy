package fetskovich.evgeny.app.features.ui.authorized.bottomnav.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.authorized.bottomnav.AuthorizedMainScreen

class AuthorizedMainScreenFeatureApi(
    private val features: Set<FeatureApi>
) : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = AuthorizedMainScreenNavigation.route
        ) { _ ->
            val bottomNavController = rememberNavController()

            AuthorizedMainScreen(
                parentNavController = navController,
                navController = bottomNavController,
                features = features,
            )
        }
    }
}