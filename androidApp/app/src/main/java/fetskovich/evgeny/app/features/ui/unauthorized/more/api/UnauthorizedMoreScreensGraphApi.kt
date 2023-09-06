package fetskovich.evgeny.app.features.ui.unauthorized.more.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.app.features.ui.unauthorized.more.other.api.OtherUnauthorizedScreenNavigation

class UnauthorizedMoreScreensGraphApi  (
    private val features: Set<FeatureApi>
) : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = UnauthorizedMoreScreensGraphNavigation.route,
            startDestination = OtherUnauthorizedScreenNavigation.route,
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