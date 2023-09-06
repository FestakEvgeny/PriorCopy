package fetskovich.evgeny.app.features.ui.authorized.products

import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.features.ui.authorized.products.mapper.BankCardToListItemMapper
import fetskovich.evgeny.app.features.ui.authorized.products.mapper.ExchangeRateToItemMapper
import fetskovich.evgeny.app.features.ui.authorized.products.mapper.NewsToListItemMapper
import fetskovich.evgeny.app.features.ui.authorized.products.mvi.ProductsScreenIntent
import fetskovich.evgeny.app.features.ui.authorized.products.mvi.ProductsScreenMviHandler
import fetskovich.evgeny.app.features.ui.authorized.products.ui.exchange.ExchangeRateUiItem
import fetskovich.evgeny.app.features.ui.authorized.products.ui.news.ShortNewsOtherListItem
import fetskovich.evgeny.app.features.viewmodel.BaseViewModel
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsIntent
import fetskovich.evgeny.domain.usecase.card.data.ObserveBankCardsUseCase
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateIntent
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateResult
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateUseCase
import fetskovich.evgeny.domain.usecase.news.GetNewsIntent
import fetskovich.evgeny.domain.usecase.news.GetNewsUseCase
import fetskovich.evgeny.entity.currency.Currency
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
    private val exchangeRateToItemMapper: ExchangeRateToItemMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    val stateFlow = mviStateHandler.stateFlow
    val actionFlow = mviStateHandler.stateFlow

    init {
        subscribeOnBankCards()
        subscribeOnNews()
        subscribeOnExchangeRate()
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
            observeBankCardsUseCase.execute(ObserveBankCardsIntent(Currency.USD))
                .map {
                    bankCardsMapper.map(it.data) to bankCardsMapper.mapBankCardsToTotalSum(it.data)
                }
                .collectLatest {
                    val (cards, totalSum) = it
                    mviStateHandler.updateBankCards(cards, totalSum)
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
                                    id = (it.news.size + 1).toString(),
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

    private fun subscribeOnExchangeRate() {
        viewModelScope.launch(coroutinesContextProvider.io) {
            observeExchangeRateUseCase.execute(
                ObserveExchangeRateIntent(Currency.USD)
            ).collectLatest {
                when (it) {
                    is ObserveExchangeRateResult.Error -> {
                        mviStateHandler.updateExchangeRateLoaded(ExchangeRateUiItem.Error)
                    }

                    ObserveExchangeRateResult.Loading -> {
                        mviStateHandler.updateExchangeRateLoaded(ExchangeRateUiItem.Loading)
                    }

                    ObserveExchangeRateResult.NoDataReceived -> {
                        mviStateHandler.updateExchangeRateLoaded(ExchangeRateUiItem.Error)
                    }

                    is ObserveExchangeRateResult.Success -> {
                        val mappedData = exchangeRateToItemMapper.mapExchangeRate(
                            monitoringCurrency = Currency.USD,
                            offCurrency = Currency.BYN,
                            item = it.result,
                        )
                        mviStateHandler.updateExchangeRateLoaded(mappedData)
                    }
                }
            }
        }
    }
}
