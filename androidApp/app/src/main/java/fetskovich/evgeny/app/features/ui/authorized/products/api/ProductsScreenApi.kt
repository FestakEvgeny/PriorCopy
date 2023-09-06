package fetskovich.evgeny.app.features.ui.authorized.products.api

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.LocalApplicationModuleComposition
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.authorized.products.ProductsScreen
import fetskovich.evgeny.app.features.ui.authorized.products.ProductsScreenViewModel
import fetskovich.evgeny.app.features.ui.authorized.products.di.productsFeatureModule
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.instance

class ProductsScreenApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = ProductsScreenNavigation.route
        ) {
            val context = LocalContext.current
            val kodein = LocalApplicationModuleComposition.current

            val screenModule = DI {
                extend(kodein)
                import(productsFeatureModule)
            }

            val viewModelProvider: ViewModelProviderFactory<ProductsScreenViewModel> by screenModule.instance()

            ProductsScreen(
                viewModel = viewModel(
                   factory = viewModelProvider,
                ),
                parentNavController = parentNavController,
                navController = navController,
            )
        }
    }
}