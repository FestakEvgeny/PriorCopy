package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class ExchangesIntent : ActionIntent {

    data class SelectTab(val tabId: ExchangesTabId) : ExchangesIntent()
}