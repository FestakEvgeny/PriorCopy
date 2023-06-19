package fetskovich.evgeny.app.features.ui.core.main.login.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.core.main.login.PASSWORD_MAX_LENGTH
import fetskovich.evgeny.app.features.ui.core.main.login.PASSWORD_MIN_LENGTH
import fetskovich.evgeny.architecture.mvi.StateHandler
import fetskovich.evgeny.domain.usecase.authorization.AuthorizeUserResult
import fetskovich.evgeny.domain.usecase.authorization.GetEmailResult
import fetskovich.evgeny.recipeskmm.app.R

class LoginScreenMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<LoginScreenState, LoginScreenAction>(
    initialState = LoginScreenState()
) {

    suspend fun handleAuthorizationResult(
        result: AuthorizeUserResult
    ) {
        when (result) {
            is AuthorizeUserResult.Authorized -> {
                processSingleAction(LoginScreenAction.NavigateToProducts)
            }

            is AuthorizeUserResult.Error -> {
                processSingleAction(LoginScreenAction.DisplayError(result.error.message ?: ""))
            }

            AuthorizeUserResult.Loading -> updateState(
                state.copy(
                    isLoading = true,
                )
            )
        }
    }

    suspend fun handleLatestEmailReceived(
        result: GetEmailResult
    ) {
        when (result) {
            is GetEmailResult.Error -> processSingleAction(
                LoginScreenAction.DisplayError(
                    result.throwable.message ?: ""
                )
            )

            is GetEmailResult.Loaded -> {
                updateState(
                    state.copy(
                        userEmail = result.email,
                    )
                )
            }

            GetEmailResult.Loading, GetEmailResult.NotExists -> {
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

    fun handleFormError(
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