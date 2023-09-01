package fetskovich.evgeny.domain.usecase.exchange

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.entity.currency.Currency
import fetskovich.evgeny.entity.exchange.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class ObserveExchangeRateUseCase(
    private val repository: ExchangeRateRepository,
) : ReactiveUseCase<ObserveExchangeRateIntent, ObserveExchangeRateResult> {

    override fun execute(
        intent: ObserveExchangeRateIntent
    ): Flow<ObserveExchangeRateResult> {
        return repository.getExchangeRate(intent.currency)
            .map {
                if (it != null) {
                    ObserveExchangeRateResult.Success(it)
                } else {
                    ObserveExchangeRateResult.NoDataReceived
                }
            }
            .onStart {
                emit(ObserveExchangeRateResult.Loading)
            }
            .catch {
                emit(ObserveExchangeRateResult.Error(it))
            }
    }
}

class ObserveExchangeRateIntent(
    val currency: Currency
) : ActionIntent

sealed class ObserveExchangeRateResult : IntentResult {

    data object Loading : ObserveExchangeRateResult()
    data object NoDataReceived : ObserveExchangeRateResult()
    data class Error(val error: Throwable) : ObserveExchangeRateResult()
    data class Success(val result: ExchangeRate) : ObserveExchangeRateResult()
}