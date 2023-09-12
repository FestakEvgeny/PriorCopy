package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.architecture.mvi.NoSingleAction
import fetskovich.evgeny.architecture.mvi.StateHandler

class ExchangesMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<ExchangesScreenState, NoSingleAction>(
    initialState = createInitialState(
        resourceProvider = resourceProvider,
    )
) {

    fun updateSelectedTab(newTabId: ExchangesTabId) {
        updateState(
            state.copy(
                selectedTabId = newTabId,
            )
        )
    }
}

private fun createInitialState(
    resourceProvider: ResourceProvider
) = ExchangesScreenState(
    tabsList = listOf(
        ExchangesTabId.ONLINE,
        ExchangesTabId.CARD,
        ExchangesTabId.CASH,
        ExchangesTabId.BANK,
    ),
    selectedTabId = ExchangesTabId.ONLINE,
)
