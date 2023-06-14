package fetskovich.evgeny.app.features.ui.main.overview.category

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.main.overview.categories.CategoriesListScreen
import fetskovich.evgeny.navigation.graph.overview.CategoriesNavigation
import fetskovich.evgeny.navigation.graph.overview.CategoryManageNavigation

class CategoryFeatureApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = CategoryManageNavigation.route
        ) {
            CategoryScreen()
        }
    }
}