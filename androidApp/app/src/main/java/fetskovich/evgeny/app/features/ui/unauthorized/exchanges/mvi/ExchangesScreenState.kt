package fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import fetskovich.evgeny.architecture.mvi.ScreenState
import fetskovich.evgeny.recipeskmm.app.R

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

@Immutable
interface ExchangeVariantState : ExchangesTabState {
    val exchanges: List<ExchangesVariant>
    val conversions: List<ExchangesVariant>
    val actualDate: String
}

@Immutable
data class ExchangesOnlineVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.ONLINE
}

@Immutable
data class ExchangesCardsVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.CARD
}

@Immutable
data class ExchangesCashVariant(
    override val exchanges: List<ExchangesVariant> = emptyList(),
    override val conversions: List<ExchangesVariant> = emptyList(),
    override val actualDate: String = "",
) : ExchangeVariantState {

    override val tabId: ExchangesTabId = ExchangesTabId.CASH
}

@Immutable
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
) {

    @DrawableRes
    fun getIsPositiveIcon() = if (isPositive) {
        R.drawable.ic_exchange_rate_up
    } else {
        R.drawable.ic_exchange_down
    }
}

enum class ExchangesTabId {
    ONLINE,
    CARD,
    CASH,
    BANK,
}