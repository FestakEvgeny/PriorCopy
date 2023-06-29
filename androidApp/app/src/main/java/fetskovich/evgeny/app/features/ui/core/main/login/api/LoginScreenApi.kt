package fetskovich.evgeny.app.features.ui.core.main.login.api

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fetskovich.evgeny.app.ApplicationModuleComposition
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreen
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.login.di.loginScreenFeatureModule
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.instance

class LoginScreenApi : FeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        parentNavController: NavHostController?,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = LoginScreenNavigation.route
        ) {
            val kodein = ApplicationModuleComposition.current

            val loginModule = DI {
                extend(kodein)
                import(loginScreenFeatureModule)
            }

            // TODO Solve the issue with recomposition
            val viewModelProvider: ViewModelProviderFactory<LoginScreenViewModel> by loginModule.instance()

            LoginScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = viewModelProvider,
                )
            )
        }
    }
}