package fetskovich.evgeny.app.features.ui.unauthorized.login.mvi

import fetskovich.evgeny.architecture.mvi.SingleAction

sealed class LoginScreenAction : SingleAction {

    object NavigateToProducts : LoginScreenAction()

    data class DisplayError(
        val message: String
    ) : LoginScreenAction()
}