package fetskovich.evgeny.app.features.ui.main.settings.general

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.navigation.graph.settings.SettingsGeneralNavigation

class GeneralSettingsFeatureApi : FeatureApi  {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = SettingsGeneralNavigation.route
        ) {
            GeneralSettingsScreen()
        }
    }
}