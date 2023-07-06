package fetskovich.evgeny.app.features.ui.singlenews.mvi

import fetskovich.evgeny.architecture.mvi.StateHandler

class SingleNewsScreenMviHandler : StateHandler<SingleNewsScreenState, SingleNewsScreenAction>(
    initialState = SingleNewsScreenState()
) {

    fun updateNewsReceivedData(
        title: String,
        htmlDescription: String
    ) {
        updateState(
            state.copy(
                title = title,
                htmlDescription = htmlDescription,
            )
        )
    }
}