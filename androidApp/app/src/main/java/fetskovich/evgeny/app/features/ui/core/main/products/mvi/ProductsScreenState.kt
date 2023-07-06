package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.news.ShortNewsListBaseItem
import fetskovich.evgeny.architecture.mvi.ScreenState

data class ProductsScreenState(
    val userEmail: String = "",
    val news: List<ShortNewsListBaseItem> = emptyList(),
    val productsSectionState: MyProductsState,
    val bottomSheetState: ProductsBottomSheetState? = null,
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