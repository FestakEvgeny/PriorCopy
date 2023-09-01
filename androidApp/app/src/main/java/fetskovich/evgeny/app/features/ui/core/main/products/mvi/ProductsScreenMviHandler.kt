package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.core.main.products.ui.exchange.ExchangeRateUiItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.cards.CardListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.myproducts.types.ProductTypeListItem
import fetskovich.evgeny.app.features.ui.core.main.products.ui.news.ShortNewsListBaseItem
import fetskovich.evgeny.architecture.mvi.StateHandler
import fetskovich.evgeny.recipeskmm.app.R

class ProductsScreenMviHandler(
    private val resourceProvider: ResourceProvider,
) : StateHandler<ProductsScreenState, ProductsScreenAction>(
    initialState = createInitialState(
        resourceProvider = resourceProvider,
    )
) {

    fun changeProductType(selectedId: ProductType) {
        val availableTypes = state.productsSectionState.availableTypes.map {
            it.copy(
                isSelected = it.id == selectedId,
            )
        }

        val updatedMyProductsState = state.productsSectionState.copy(
            availableTypes = availableTypes,
            selectedType = selectedId,
        )

        updateState(
            state.copy(
                productsSectionState = updatedMyProductsState,
            )
        )
    }

    fun updateAddProductDialogVisibility(isVisible: Boolean) {
        val sheetState = if (isVisible) {
            ProductsBottomSheetState.AddProduct
        } else {
            null
        }

        updateState(
            state.copy(
                bottomSheetState = sheetState,
            )
        )
    }

    fun updateBankCards(
        cards: List<CardListItem>
    ) {
        val products = state.productsSectionState.copy(
            cardsList = cards,
        )

        updateState(
            state.copy(
                productsSectionState = products,
            )
        )
    }

    fun updateNews(
        news: List<ShortNewsListBaseItem>
    ) {
        val news = state.copy(
            news = news
        )

        updateState(news)
    }

    fun updateExchangeRateLoaded(
        item: ExchangeRateUiItem
    ) {
        updateState(
            state.copy(
                exchangeRateState = item,
            )
        )
    }
}

private fun createInitialState(
    resourceProvider: ResourceProvider,
): ProductsScreenState {
    val initiallySelectedType = ProductType.CARDS

    return ProductsScreenState(
        userEmail = "",
        productsSectionState = MyProductsState(
            selectedType = initiallySelectedType,
            availableTypes = listOf(
                ProductTypeListItem(
                    id = ProductType.CARDS,
                    text = resourceProvider.provideString(R.string.products_screen_my_products_cards),
                    isSelected = true,
                ),
                ProductTypeListItem(
                    id = ProductType.CREDITS,
                    text = resourceProvider.provideString(R.string.products_screen_my_products_credits),
                    isSelected = false,
                ),
                ProductTypeListItem(
                    id = ProductType.DEPOSITS,
                    text = resourceProvider.provideString(R.string.products_screen_my_products_deposits),
                    isSelected = false,
                )
            ),
            cardsList = emptyList(),
            creditsList = emptyList(),
            depositsList = emptyList(),
        )
    )
}