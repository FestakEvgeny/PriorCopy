package fetskovich.evgeny.app.features.ui.singlenews.api

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.LocalApplicationModuleComposition
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.singlenews.SingleNewsScreen
import fetskovich.evgeny.app.features.ui.singlenews.SingleNewsViewModel
import fetskovich.evgeny.app.features.ui.singlenews.di.singleNewsFeatureModule
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.instance
import org.kodein.di.with

class SingleNewsFeatureApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = SingleNewsFeatureNavigation.route,
            arguments = SingleNewsFeatureNavigation.args,
        ) {

            val newsId = SingleNewsFeatureNavigation.getNewsId(it)

            val kodein = LocalApplicationModuleComposition.current

            val screenModule = DI {
                extend(kodein)
                constant("newsId") with newsId
                import(singleNewsFeatureModule)
            }

            val viewModelProvider: ViewModelProviderFactory<SingleNewsViewModel> by screenModule.instance()

            SingleNewsScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = viewModelProvider,
                )
            )
        }
    }
}