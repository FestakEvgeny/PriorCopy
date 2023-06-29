package fetskovich.evgeny.domain.usecase.card

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase

class ValidateCardExpirationUseCase : UseCase<ValidateCardExpirationIntent, ValidatedCardExpirationResult> {

    override suspend fun execute(
        intent: ValidateCardExpirationIntent
    ): ValidatedCardExpirationResult {
        val isValid = intent.expiration.length == 4 // TODO Apply normal validation
        return ValidatedCardExpirationResult(isValid)
    }
}

class ValidateCardExpirationIntent(
    val expiration: String
) : ActionIntent

class ValidatedCardExpirationResult(val isValid: Boolean) : IntentResult
