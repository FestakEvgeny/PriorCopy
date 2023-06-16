package fetskovich.evgeny.app.features.ui.core.main.login

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenAction
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.auth.GetLatestEmailIntent
import fetskovich.evgeny.domain.auth.GetLatestEmailUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 64

class LoginScreenViewModel(
    private val mviHandler: LoginScreenMviHandler,
    private val getLatestEmailUseCase: GetLatestEmailUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : BaseViewModel() {

    val stateFlow = mviHandler.stateFlow

    init {
        viewModelScope.launch {
            getLatestEmailUseCase.execute(GetLatestEmailIntent)
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
            val isPasswordValid = stateFlow.value.userPassword.let {
                it.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH
            }

            val isEmailValid = stateFlow.value.userEmail.let {
                Patterns.EMAIL_ADDRESS.matcher(it).matches()
            }

            if (!isPasswordValid || !isEmailValid) {
                mviHandler.handleFormError(
                    isPasswordValid = isPasswordValid,
                    isEmailValid = isEmailValid,
                )
                return@launch
            }

            // TODO Execute Login async logic
        }
    }
}