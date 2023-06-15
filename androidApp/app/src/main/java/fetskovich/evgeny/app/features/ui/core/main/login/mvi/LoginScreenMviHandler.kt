package fetskovich.evgeny.app.features.ui.core.main.login.mvi

import fetskovich.evgeny.architecture.mvi.StateHandler
import fetskovich.evgeny.domain.auth.GetLatestEmailResult

class LoginScreenMviHandler : StateHandler<LoginScreenState, LoginScreenAction>(
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
                        latestEmail = result.email,
                    )
                )
            }

            GetLatestEmailResult.Loading, GetLatestEmailResult.NotExists -> {
                // do nothing
            }
        }
    }
}