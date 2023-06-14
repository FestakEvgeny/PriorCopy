package fetskovich.evgeny.app.features.ui.main.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.navigation.graph.settings.SettingsGeneralNavigation
import fetskovich.evgeny.navigation.graph.settings.SettingsRootNavGraph

class SettingsFeatureApi(
    private val features: Set<FeatureApi>
) : FeatureApi {

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = SettingsRootNavGraph.route,
            route = SettingsGeneralNavigation.route
        ) {
            features.forEach { feature ->
                register(
                    feature = feature,
                    navController = navController,
                    parentNavController = requireNotNull(parentNavController),
                    modifier = modifier
                )
            }
        }
    }
}