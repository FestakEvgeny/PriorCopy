package fetskovich.evgeny.app.features.ui.core.main.login.mvi

import fetskovich.evgeny.architecture.mvi.ScreenState

data class LoginScreenState(
    val latestEmail: String = ""
) : ScreenState