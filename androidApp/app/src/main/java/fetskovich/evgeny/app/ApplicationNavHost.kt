package fetskovich.evgeny.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.register
import fetskovich.evgeny.navigation.graph.overview.OverviewRootNavGraph

@Composable
fun ApplicationNavHost(
    features: Set<FeatureApi>
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OverviewRootNavGraph.route,
    ) {
        features.forEach { feature ->
            register(
                feature = feature,
                navController = navController
            )
        }
    }
}