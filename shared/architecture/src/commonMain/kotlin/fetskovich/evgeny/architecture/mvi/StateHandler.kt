package fetskovich.evgeny.architecture.mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class StateHandler<S : ScreenState, A : SingleAction>(
    private val initialState: S,
) {

    private val mutableState = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = mutableState
    val state: S get() = stateFlow.value

    private val singleActionChannel = Channel<A>()
    val singleAction = singleActionChannel.receiveAsFlow()

    suspend fun processSingleAction(
        action: A
    ) {
        singleActionChannel.send(action)
    }

    protected fun updateState(
        updatedState: S
    ) {
        mutableState.update { updatedState }
    }
}
