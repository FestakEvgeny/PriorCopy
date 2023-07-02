package fetskovich.evgeny.app.features.ui.core.main.products

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.BankCardToListItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsScreenViewModel(
    private val mviStateHandler: ProductsScreenMviHandler,
    private val observeBankCardsUseCase: ObserveBankCardsUseCase,
    private val bankCardsMapper: BankCardToListItemMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : BaseViewModel() {

    val stateFlow = mviStateHandler.stateFlow
    val actionFlow = mviStateHandler.stateFlow

    init {
        subscribeOnBankCards()
    }

    override fun processIntent(intent: ActionIntent) {
        if (intent is ProductsScreenIntent) {
            when (intent) {
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

    private fun subscribeOnBankCards() {
        viewModelScope.launch(coroutinesContextProvider.io) {
            observeBankCardsUseCase.execute(ObserveBankCardsIntent)
                .map {
                    bankCardsMapper.map(it.data)
                }
                .collectLatest {
                    mviStateHandler.updateBankCards(it)
                }
        }
    }
}
