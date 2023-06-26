package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class ProductsScreenIntent : ActionIntent {

    object ShowAddProductDialog : ProductsScreenIntent()
    object DismissAddProductDialog: ProductsScreenIntent()

    data class ChangeProductType(
        val type: ProductType
    ) : ProductsScreenIntent()
}