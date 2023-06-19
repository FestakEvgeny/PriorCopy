package fetskovich.evgeny.app.features.ui.core.main.login.api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import fetskovich.evgeny.app.core.coroutines.CoroutineContextProviderImpl
import fetskovich.evgeny.app.core.resources.ResourceProviderImpl
import fetskovich.evgeny.app.features.ui.FeatureApi
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreen
import fetskovich.evgeny.app.features.ui.core.main.login.LoginScreenViewModel
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.ViewModelProviderFactory
import fetskovich.evgeny.data.auth.AuthorizationRepositoryImpl
import fetskovich.evgeny.data.user.UserSettingsStorageImpl
import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserUseCase
import fetskovich.evgeny.domain.usecase.authorization.GetEmailUseCase
import fetskovich.evgeny.domain.usecase.authorization.SaveEmailUseCase

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
            val context = LocalContext.current

            LoginScreen(
                navController = navController,
                viewModel = viewModel(
                    factory = ViewModelProviderFactory {
                        LoginScreenViewModel(
                            mviHandler = LoginScreenMviHandler(
                                resourceProvider = ResourceProviderImpl(context)
                            ),
                            getEmailUseCase = GetEmailUseCase(
                                userSettingsStorage = UserSettingsStorageImpl(),
                                coroutinesContextProvider = CoroutineContextProviderImpl(),
                            ),
                            authorizeUseCase = AuthorizeUserUseCase(
                                authorizationRepository = AuthorizationRepositoryImpl(),
                                saveEmailUseCase = SaveEmailUseCase(
                                    settingsStorage = UserSettingsStorageImpl(),
                                ),
                                coroutinesContextProvider = CoroutineContextProviderImpl(),
                            ),
                            coroutinesContextProvider = CoroutineContextProviderImpl(),
                        )
                    },
                ),
            )
        }
    }
}