package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.architecture.mvi.ScreenState

data class ProductsScreenState(
    val userEmail: String = "",
    val productsSectionState: MyProductsState? = null,
): ScreenState

data class MyProductsState(
    val selectedType: ProductType,
    val availableTypes: List<ProductType>,
    val cardsList: List<CardListItem>,
    val creditsList: List<Any>,
    val depositsList: List<Any>,
)

enum class ProductType {
    CARDS,
    CREDITS,
    DEPOSITS,
}