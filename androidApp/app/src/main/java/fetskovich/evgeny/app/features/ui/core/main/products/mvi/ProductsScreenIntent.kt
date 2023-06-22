package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class ProductsScreenIntent : ActionIntent {

    data class ChangeProductType(
        val type: ProductType
    ) : ProductsScreenIntent()
}