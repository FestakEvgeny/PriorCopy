package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.architecture.mvi.SingleAction

sealed class ProductsScreenAction : SingleAction {

    object NavigateToCardCreation : ProductsScreenAction()
}