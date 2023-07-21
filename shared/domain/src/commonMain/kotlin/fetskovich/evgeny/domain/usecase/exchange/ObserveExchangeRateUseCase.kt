package fetskovich.evgeny.domain.usecase.exchange

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveExchangeRateUseCase(
    private val repository: ExchangeRateRepository,
) : ReactiveUseCase<ObserveExchangeRateIntent, ObserveExchangeRateResult> {

    override fun execute(
        intent: ObserveExchangeRateIntent
    ): Flow<ObserveExchangeRateResult> {
        return repository.getExchangeRate(intent.currency)
            .map {
                it?.fold(
                    onSuccess = {
                        ObserveExchangeRateResult.Success(it)
                    },
                    onFailure = {
                        ObserveExchangeRateResult.Error(it)
                    }
                ) ?: ObserveExchangeRateResult.Loading
            }
    }
}

class ObserveExchangeRateIntent(
    val currency: Currency
) : ActionIntent

sealed class ObserveExchangeRateResult : IntentResult {

    object Loading : ObserveExchangeRateResult()
    class Error(val error: Throwable) : ObserveExchangeRateResult()
    class Success(val result: ExchangeRate) : ObserveExchangeRateResult()
}