package fetskovich.evgeny.app.features.ui.addbankcard.api

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.core.resources.ResourceProviderImpl
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardScreen
import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardViewModel
import fetskovich.evgeny.app.features.ui.addbankcard.mvi.AddAnotherBankCardScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory

class AddAnotherBankCardFeatureApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = AddAnotherBankCardNavigation.route
        ) {
            val context = LocalContext.current

            AddAnotherBankCardScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = ViewModelProviderFactory {
                        AddAnotherBankCardViewModel(
                            mviStateHandler = AddAnotherBankCardScreenMviHandler(
                                resourceProvider = ResourceProviderImpl(context)
                            ),
                        )
                    }
                ),
            )
        }
    }
}