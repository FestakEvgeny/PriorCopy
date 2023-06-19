package fetskovich.evgeny.app.features.ui.core.main.products.mvi

import fetskovich.evgeny.architecture.mvi.ActionIntent

sealed class ProductsScreenIntent : ActionIntent {

    object AddNewBankingCard : ProductsScreenIntent()
}