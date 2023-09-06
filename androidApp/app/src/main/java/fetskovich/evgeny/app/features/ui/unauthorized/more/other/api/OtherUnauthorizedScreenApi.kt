package fetskovich.evgeny.app.features.ui.unauthorized.more.other.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.unauthorized.more.other.OtherUnauthorizedScreen

class OtherUnauthorizedScreenApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = OtherUnauthorizedScreenNavigation.route
        ) {
            OtherUnauthorizedScreen()
        }
    }
}