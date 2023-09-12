package fetskovich.evgeny.app.features.ui.unauthorized.exchanges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mapper.ExchangesRatesMapper
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesIntent
import fetskovich.evgeny.app.features.ui.unauthorized.exchanges.mvi.ExchangesMviHandler
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateIntent
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateResult
import fetskovich.evgeny.domain.usecase.exchange.ObserveExchangeRateUseCase
import fetskovich.evgeny.entity.currency.Currency
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExchangesViewModel(
    private val mviHandler: ExchangesMviHandler,
    private val exchangesRatesMapper: ExchangesRatesMapper,
    private val observeExchangeRateUseCase: ObserveExchangeRateUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ViewModel() {

    val stateFlow = mviHandler.stateFlow

    init {
        subscribeOnExchangeRate()
    }

    fun processIntent(intent: ExchangesIntent) {
        when (intent) {
            is ExchangesIntent.SelectTab -> {
                mviHandler.updateSelectedTab(intent.tabId)
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
                        // do nothing
                    }

                    ObserveExchangeRateResult.Loading -> {
                        // do nothing
                    }

                    ObserveExchangeRateResult.NoDataReceived -> {
                        // do nothing
                    }

                    is ObserveExchangeRateResult.Success -> {

                    }
                }
            }
        }
    }
}