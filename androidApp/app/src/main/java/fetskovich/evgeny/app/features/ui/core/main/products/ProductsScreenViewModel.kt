package fetskovich.evgeny.app.features.ui.core.main.products

import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.mvi.ActionIntent

class ProductsScreenViewModel(
    private val mviStateHandler: ProductsScreenMviHandler,
) : BaseViewModel() {

    val stateFlow = mviStateHandler.stateFlow
    val actionFlow = mviStateHandler.stateFlow

    override fun processIntent(intent: ActionIntent) {
        if (intent is ProductsScreenIntent) {
            when(intent) {
                is ProductsScreenIntent.ChangeProductType -> mviStateHandler.changeProductType(
                    selectedId = intent.type,
                )

                ProductsScreenIntent.ShowAddProductDialog -> mviStateHandler.updateAddProductDialogVisibility(
                    isVisible = true,
                )
                ProductsScreenIntent.DismissAddProductDialog -> mviStateHandler.updateAddProductDialogVisibility(
                    isVisible = false,
                )
            }
        }
    }
}