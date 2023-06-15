package fetskovich.evgeny.app.features.ui.core.main.login

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.core.main.login.mvi.LoginScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.auth.GetLatestEmailIntent
import fetskovich.evgeny.domain.auth.GetLatestEmailUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val mviHandler: LoginScreenMviHandler,
    private val getLatestEmailUseCase: GetLatestEmailUseCase
) : BaseViewModel() {

    val stateFlow = mviHandler.stateFlow

    init {
        viewModelScope.launch {
            getLatestEmailUseCase.execute(GetLatestEmailIntent)
                .collectLatest(mviHandler::handleLatestEmailReceived)
        }
    }

    override fun processIntent(intent: ActionIntent) {
        // do nothing
    }
}