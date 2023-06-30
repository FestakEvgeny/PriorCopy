package fetskovich.evgeny.app.features.ui.addbankcard.api

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.LocalApplicationModuleComposition
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardScreen
import fetskovich.evgeny.app.features.ui.addbankcard.AddAnotherBankCardViewModel
import fetskovich.evgeny.app.features.ui.addbankcard.di.addAnotherBankCardFeatureModule
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.domain.bank.BankCardRepository
import org.kodein.di.DI
import org.kodein.di.instance

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

            val kodein = LocalApplicationModuleComposition.current

            val screenModule = DI {
                extend(kodein)
                import(addAnotherBankCardFeatureModule)
            }

            val databaseRepository: BankCardRepository by screenModule.instance()
            databaseRepository.createBank()

            val viewModelProvider: ViewModelProviderFactory<AddAnotherBankCardViewModel> by screenModule.instance()

            AddAnotherBankCardScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = viewModelProvider,
                ),
            )
        }
    }
}