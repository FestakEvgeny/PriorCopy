package fetskovich.evgeny.app.features.ui.unauthorized.cashpoints.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.unauthorized.cashpoints.CashpointsScreen

class CashpointsScreenApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = CashpointsScreenNavigation.route
        ) {
            CashpointsScreen()
        }
    }
}