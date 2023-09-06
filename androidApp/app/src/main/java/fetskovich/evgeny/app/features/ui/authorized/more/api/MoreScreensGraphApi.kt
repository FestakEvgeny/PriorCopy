package fetskovich.evgeny.app.features.ui.authorized.more.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.authorized.more.other.api.OtherScreenNavigation
import fetskovich.evgeny.app.features.ui.register

class MoreScreensGraphApi (
    private val features: Set<FeatureApi>
) : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = MoreScreensGraphNavigation.route,
            startDestination = OtherScreenNavigation.route,
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