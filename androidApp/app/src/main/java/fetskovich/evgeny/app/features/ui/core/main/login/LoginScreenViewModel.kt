package fetskovich.evgeny.app.features.ui.core.main.login

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserIntent
import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserUseCase
import fetskovich.evgeny.domain.usecase.authorization.GetEmailIntent
import fetskovich.evgeny.domain.usecase.authorization.GetEmailUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 64

class LoginScreenViewModel(
    private val mviHandler: LoginScreenMviHandler,
    private val getEmailUseCase: GetEmailUseCase,
    private val authorizeUseCase: AuthorizeUserUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : BaseViewModel() {

    val stateFlow = mviHandler.stateFlow
    val actionFlow = mviHandler.singleAction

    init {
        viewModelScope.launch {
            getEmailUseCase.execute(GetEmailIntent)
                .collectLatest(mviHandler::handleLatestEmailReceived)
        }
    }

    override fun processIntent(intent: ActionIntent) {
        if (intent is LoginScreenIntent) {
            when (intent) {
                LoginScreenIntent.LoginUser -> login()
                LoginScreenIntent.ReversePasswordVisibility -> mviHandler.reversePasswordVisibility()
                is LoginScreenIntent.UpdateEmail -> mviHandler.updateEmail(intent.email)
                is LoginScreenIntent.UpdatePassword -> mviHandler.updatePassword(intent.password)
            }
        }
    }

    private fun login() {
        viewModelScope.launch(coroutinesContextProvider.io) {
            val state = stateFlow.value

            val isPasswordValid = state.userPassword.let(::validatePassword)

            val isEmailValid = state.userEmail.let(::validateEmail)

            if (!isPasswordValid || !isEmailValid) {
                mviHandler.handleFormError(
                    isPasswordValid = isPasswordValid,
                    isEmailValid = isEmailValid,
                )
                return@launch
            }

            authorizeUseCase.execute(
                AuthorizeUserIntent(
                    email = stateFlow.value.userEmail,
                    password = stateFlow.value.userPassword,
                )
            ).collectLatest(mviHandler::handleAuthorizationResult)
        }
    }

    private fun validatePassword(password: String) = password.let {
        it.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH
    }

    private fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}