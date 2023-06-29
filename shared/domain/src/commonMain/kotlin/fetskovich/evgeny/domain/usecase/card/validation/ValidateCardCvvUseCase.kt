package fetskovich.evgeny.domain.usecase.card.validation

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase

class ValidateCardCvvUseCase : UseCase<ValidateCardCvvIntent, ValidatedCardCvvResult> {

    override suspend fun execute(
        intent: ValidateCardCvvIntent
    ): ValidatedCardCvvResult {
        val isValid = intent.cvv.length == 3
        return ValidatedCardCvvResult(isValid)
    }
}

class ValidateCardCvvIntent(
    val cvv: String
) : ActionIntent

class ValidatedCardCvvResult(val isValid: Boolean) : IntentResult
