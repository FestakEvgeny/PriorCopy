package fetskovich.evgeny.app.features.ui.core.main.products.api

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.core.resources.ResourceProviderImpl
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.main.products.ProductsScreen
import fetskovich.evgeny.app.features.ui.core.main.products.ProductsScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory

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

            ProductsScreen(
                viewModel = viewModel(
                   factory = ViewModelProviderFactory {
                        ProductsScreenViewModel(
                            mviStateHandler = ProductsScreenMviHandler(
                                resourceProvider = ResourceProviderImpl(context)
                            ),
                        )
                    }
                ),
                parentNavController = parentNavController,
                navController = navController,
            )
        }
    }
}