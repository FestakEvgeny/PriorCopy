package fetskovich.evgeny.app.features.ui.unauthorized.login.mvi

import fetskovich.evgeny.architecture.mvi.ScreenState

data class LoginScreenState(
    val userEmail: String = "fiatskovich.w@gmail.com",
    val userPassword: String = "qweqweqweqweqwe",
    val isPasswordVisible: Boolean = false,
    val passwordErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val isLoading: Boolean = false,
) : ScreenState

