package fetskovich.evgeny.app.features.ui.core.main.products

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.BankCardToListItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mapper.NewsToListItemMapper
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.core.main.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.ui.core.main.products.ui.news.ShortNewsOtherListItem
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsUseCase
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateUseCase
import fetskovich.evgeny.domain.usecase.news.GetNewsIntent
import fetskovich.evgeny.domain.usecase.news.GetNewsUseCase
import fetskovich.evgeny.recipeskmm.app.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsScreenViewModel(
    private val mviStateHandler: ProductsScreenMviHandler,
    private val observeBankCardsUseCase: ObserveBankCardsUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val observeExchangeRateUseCase: ObserveExchangeRateUseCase,
    private val bankCardsMapper: BankCardToListItemMapper,
    private val newsToListItemMapper: NewsToListItemMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    val stateFlow = mviStateHandler.stateFlow
    val actionFlow = mviStateHandler.stateFlow

    init {
        subscribeOnBankCards()
        subscribeOnNews()
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
                .map { bankCardsMapper.map(it.data) }
                .collectLatest {
                    mviStateHandler.updateBankCards(it)
                }
        }
    }

    private fun subscribeOnNews() {
        viewModelScope.launch(coroutinesContextProvider.io) {
            getNewsUseCase.execute(GetNewsIntent)
                .map {
                    newsToListItemMapper.map(it.news)
                        .toMutableList().apply {
                            add(
                                ShortNewsOtherListItem(
                                    id = "6", // just a hardcoded id
                                    title = resourceProvider.provideString(R.string.products_screen_news_watch_more)
                                )
                            )
                        }
                }
                .collectLatest {
                    mviStateHandler.updateNews(it)
                }
        }
    }
}
