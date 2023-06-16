package fetskovich.evgeny.app.features.ui.core.main.login.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.core.main.login.PASSWORD_MAX_LENGTH
import fetskovich.evgeny.app.features.ui.core.main.login.PASSWORD_MIN_LENGTH
import fetskovich.evgeny.architecture.mvi.StateHandler
import fetskovich.evgeny.domain.auth.GetLatestEmailResult
import fetskovich.evgeny.recipeskmm.app.R

class LoginScreenMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<LoginScreenState, LoginScreenAction>(
    initialState = LoginScreenState()
) {

    suspend fun handleLatestEmailReceived(
        result: GetLatestEmailResult
    ) {
        when (result) {
            is GetLatestEmailResult.Error -> processSingleAction(
                LoginScreenAction.DisplayError(
                    result.throwable.message ?: ""
                )
            )

            is GetLatestEmailResult.Loaded -> {
                updateState(
                    state.copy(
                        userEmail = result.email,
                    )
                )
            }

            GetLatestEmailResult.Loading, GetLatestEmailResult.NotExists -> {
                // do nothing
            }
        }
    }

    fun updateEmail(email: String) {
        updateState(
            state.copy(
                userEmail = email,
            )
        )
    }

    fun updatePassword(password: String) {
        updateState(
            state.copy(
                userPassword = password,
            )
        )
    }

    fun reversePasswordVisibility() {
        updateState(
            state.copy(
                isPasswordVisible = state.isPasswordVisible.not()
            )
        )
    }

    suspend fun handleFormError(
        isPasswordValid: Boolean,
        isEmailValid: Boolean
    ) {
        val passwordErrorText = if (isPasswordValid) {
            null
        } else {
            resourceProvider.provideString(
                R.string.login_screen_password_invalid,
                PASSWORD_MIN_LENGTH,
                PASSWORD_MAX_LENGTH
            )
        }

        val emailErrorText = if (isEmailValid) {
            null
        } else {
            resourceProvider.provideString(R.string.login_screen_email_invalid)
        }

        updateState(
            state.copy(
                passwordErrorMessage = passwordErrorText,
                emailErrorMessage = emailErrorText,
            )
        )
    }
}