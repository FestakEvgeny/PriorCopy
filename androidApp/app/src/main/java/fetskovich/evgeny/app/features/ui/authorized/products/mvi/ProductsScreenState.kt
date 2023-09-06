package fetskovich.evgeny.app.features.ui.authorized.products.mvi

import fetskovich.evgeny.app.features.ui.authorized.products.ui.exchange.ExchangeRateUiItem
import fetskovich.evgeny.app.features.ui.authorized.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.app.features.ui.authorized.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.app.features.ui.authorized.products.ui.news.ShortNewsListBaseItem
import fetskovich.evgeny.architecture.mvi.ScreenState

data class ProductsScreenState(
    val userEmail: String = "",
    val news: List<ShortNewsListBaseItem> = emptyList(),
    val productsSectionState: MyProductsState,
    val bottomSheetState: ProductsBottomSheetState? = null,
    val exchangeRateState: ExchangeRateUiItem = ExchangeRateUiItem.Loading,
    val totalCardsSum: String?,
): ScreenState

data class MyProductsState(
    val selectedType: ProductType,
    val availableTypes: List<ProductTypeListItem>,
    val cardsList: List<CardListItem>,
    val creditsList: List<Any>,
    val depositsList: List<Any>,
)

enum class ProductType {
    CARDS,
    CREDITS,
    DEPOSITS,
}

sealed class ProductsBottomSheetState {

    object AddProduct : ProductsBottomSheetState()
}