package fetskovich.evgeny.domain.usecase.card.expiration

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class ExpirationDateToTimestampUseCase :
    UseCase<ExpirationDateToTimestampIntent, ExpirationDateToTimestampResult> {

    override suspend fun execute(
        intent: ExpirationDateToTimestampIntent
    ): ExpirationDateToTimestampResult {
        val timestamp = intent.expirationDate
        val parsed = if (timestamp.contains("/")) {
            // parse format 02/27
            timestamp.split("/")
        } else {
            // parse format 0227
            val month = timestamp.substring(0, 2)
            val years = timestamp.substring(2, 4)
            listOf(month, years)
        }

        val month = parsed[0]
        val year = "20" + parsed[1] // temp solution, apply normal logic

        try {
            val epochSeconds = LocalDateTime(
                year = year.toInt(),
                monthNumber = month.toInt(),
                dayOfMonth = 1,
                hour = 0,
                minute = 0,
                second = 0,
                nanosecond = 0,
            ).toInstant(TimeZone.currentSystemDefault()).epochSeconds

            return ExpirationDateToTimestampResult.Success(
                timestamp = epochSeconds
            )
        } catch (e: Exception) {
            return ExpirationDateToTimestampResult.InvalidInput
        }
    }
}

data class ExpirationDateToTimestampIntent(
    val expirationDate: String,
) : ActionIntent

sealed class ExpirationDateToTimestampResult : IntentResult {

    object InvalidInput : ExpirationDateToTimestampResult()

    data class Success(
        val timestamp: Long
    ) : ExpirationDateToTimestampResult()
}