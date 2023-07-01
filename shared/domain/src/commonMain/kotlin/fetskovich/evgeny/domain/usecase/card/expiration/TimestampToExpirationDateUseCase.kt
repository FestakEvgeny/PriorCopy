package fetskovich.evgeny.domain.usecase.card.expiration

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class TimestampToExpirationDateUseCase :
    UseCase<TimestampToExpirationDateIntent, TimestampToExpirationDateResult> {

    override suspend fun execute(
        intent: TimestampToExpirationDateIntent
    ): TimestampToExpirationDateResult {
        val timestamp = intent.timestamp
        val instant = Instant.fromEpochSeconds(timestamp)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val lastYearSymbols = dateTime.year.toString().takeLast(2)

        return TimestampToExpirationDateResult(
            result = "${dateTime.monthNumber}/$lastYearSymbols"
        )
    }
}

data class TimestampToExpirationDateIntent(
    val timestamp: Long,
) : ActionIntent

data class TimestampToExpirationDateResult(
    val result: String
) : IntentResult