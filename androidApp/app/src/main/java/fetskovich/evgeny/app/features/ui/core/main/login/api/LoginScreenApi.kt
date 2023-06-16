package fetskovich.evgeny.app.features.ui.core.main.login.api

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import fetskovich.evgeny.app.core.coroutines.CoroutineContextProviderImpl
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreen
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.data.auth.AuthorizationRepositoryImpl
import fetskovich.evgeny.domain.auth.GetLatestEmailUseCase

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
            LoginScreen(
                viewModel = viewModel(
                    factory = ViewModelProviderFactory {
                        LoginScreenViewModel(
                            mviHandler = LoginScreenMviHandler(),
                            getLatestEmailUseCase = GetLatestEmailUseCase(
                                authorizationRepository = AuthorizationRepositoryImpl(),
                                coroutinesContextProvider = CoroutineContextProviderImpl(),
                            )
                        )
                    },
                ),
            )
        }
    }
}