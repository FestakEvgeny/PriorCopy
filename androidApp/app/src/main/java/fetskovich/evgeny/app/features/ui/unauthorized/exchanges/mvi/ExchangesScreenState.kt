package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi

import fetskovich.evgeny.architecture.mvi.ScreenState

data class ExchangesScreenState(
    val tabsList: List<ExchangesTabId>,
    val selectedTabId: ExchangesTabId,
    val onlineVariant: ExchangesOnlineVariant = ExchangesOnlineVariant(),
    val cardVariant: ExchangesCardsVariant = ExchangesCardsVariant(),
    val cashVariant: ExchangesCashVariant = ExchangesCashVariant(),
    val bankVariant: ExchangesBankVariant = ExchangesBankVariant(),
) : ScreenState

interface ExchangesTabState {
    val tabId: ExchangesTabId
}

interface ExchangeVariantState : ExchangesTabState {
    val exchanges: List<ExchangesVariant>
    val conversions: List<ExchangesVariant>
    val actualDate: String
}

data class ExchangesOnlineVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.ONLINE
}

data class ExchangesCardsVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.CARD
}

data class ExchangesCashVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.CASH
}

data class ExchangesBankVariant(
    val exchanges: List<ExchangeCourseVariant> = emptyList(),
    val actualDate: String = "",
) : ExchangesTabState {

    override val tabId: ExchangesTabId = ExchangesTabId.BANK
}

data class ExchangeCourseVariant(
    val name: String,
    val exchange: String,
    val isPositive: Boolean,
)

data class ExchangesVariant(
    val name: String,
    val buyPrice: ExchangesPrice,
    val sellPrice: ExchangesPrice,
)

data class ExchangesPrice(
    val price: String,
    val isPositive: Boolean,
)

enum class ExchangesTabId {
    ONLINE,
    CARD,
    CASH,
    BANK,
}