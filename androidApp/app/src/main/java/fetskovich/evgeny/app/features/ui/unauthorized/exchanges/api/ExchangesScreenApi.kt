package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.api

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.LocalApplicationModuleComposition
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ExchangesScreen
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.ExchangesViewModel
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.di.exchangesScreenFeatureModule
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.instance

class ExchangesScreenApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = ExchangesScreenNavigation.route
        ) {
            val kodein = LocalApplicationModuleComposition.current

            val exchangesScreenModule = DI {
                extend(kodein)
                import(exchangesScreenFeatureModule)
            }

            val viewModelProvider: ViewModelProviderFactory<ExchangesViewModel> by exchangesScreenModule.instance()

            ExchangesScreen(
                viewModel = viewModel(
                    factory = viewModelProvider,
                )
            )
        }
    }
}