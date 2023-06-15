package fetskovich.evgeny.architecture.mvi

import kotlinx.coroutines.flow.StateFlow

interface StateHandler {

    val state: StateFlow<ScreenState>
}