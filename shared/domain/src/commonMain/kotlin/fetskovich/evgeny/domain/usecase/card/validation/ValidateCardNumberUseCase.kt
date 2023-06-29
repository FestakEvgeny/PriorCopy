package fetskovich.evgeny.domain.usecase.card.validation

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase

class ValidateCardNumberUseCase : UseCase<ValidateCardIntent, ValidatedCardResult> {

    override suspend fun execute(
        intent: ValidateCardIntent
    ): ValidatedCardResult {
        val value = intent.card

        if (value.isEmpty()) {
            return ValidatedCardResult(false)
        }

        // Luhn's algortihm.
        var checksum = 0
        for (i in value.length - 1 downTo 0 step 2) {
            checksum += value[i] - '0'
        }
        for (i in value.length - 2 downTo 0 step 2) {
            val n: Int = (value[i] - '0') * 2
            checksum += if (n > 9) n - 9 else n
        }
        val isValid = checksum % 10 == 0

        return ValidatedCardResult(isValid)
    }
}

data class ValidateCardIntent(
    val card: String
) : ActionIntent

class ValidatedCardResult(val isValid: Boolean) : IntentResult