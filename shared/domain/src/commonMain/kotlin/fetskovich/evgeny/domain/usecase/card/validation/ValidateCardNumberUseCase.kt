package fetskovich.evgeny.domain.usecase.card.validation

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase

class ValidateCardNumberUseCase : UseCase<ValidateCardIntent, ValidatedCardResult> {

    override suspend fun execute(
        intent: ValidateCardIntent
    ): ValidatedCardResult {
        val isValid = intent.card.length == 16

        return ValidatedCardResult(isValid)
    }
}

data class ValidateCardIntent(
    val card: String
) : ActionIntent

class ValidatedCardResult(val isValid: Boolean) : IntentResult