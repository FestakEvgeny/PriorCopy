package fetskovich.evgeny.app.features.ui.unauthorized.login.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class LoginScreenIntent : ActionIntent {

    class UpdateEmail(
        val email: String
    ) : LoginScreenIntent()

    class UpdatePassword(
        val password: String
    ) : LoginScreenIntent()

    object ReversePasswordVisibility : LoginScreenIntent()

    object LoginUser : LoginScreenIntent()
}
