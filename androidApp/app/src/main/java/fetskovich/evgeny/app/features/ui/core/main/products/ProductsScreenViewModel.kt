package fetskovich.evgeny.app.features.ui.core.main.products

import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.mvi.ActionIntent

class ProductsScreenViewModel(
    private val mviStateHandler: ProductsScreenMviHandler,
) : BaseViewModel() {

    override fun processIntent(intent: ActionIntent) {
        TODO("Not yet implemented")
    }
}