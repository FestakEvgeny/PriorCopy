package fetskovich.evgeny.app.features.ui.authorized.products.mvi

import fetskovich.evgeny.architecture.mvi.SingleAction

sealed class ProductsScreenAction : SingleAction {

    object NavigateToCardCreation : ProductsScreenAction()
}