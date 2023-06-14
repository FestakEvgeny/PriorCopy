package fetskovich.evgeny.app.features.ui.main.overview

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.navigation.graph.overview.CategoriesNavigation
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph

class OverviewFeatureApi(
    private val features: Set<FeatureApi>
) : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = CategoriesNavigation.route,
            route = OverviewRootNavGraph.route,
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