package fetskovich.evgeny.app.features.ui.core.main.login.mvi

import fetskovich.evgeny.architecture.mvi.ScreenState

data class LoginScreenState(
    val userEmail: String = "",
    val userPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val passwordErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val isLoading: Boolean = false,
) : ScreenState

