package fetskovich.evgeny.domain.usecase.card.validation

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase
import fetskovich.evgeny.entity.card.BankCardType

class IdentifyCardUseCase :
    UseCase<IdentifyBankCardIntent, BankCardIdentifiedResult> {

    private val jcbRegex = Regex("^(?:2131|1800|35)[0-9]{0,}$")
    private val visaRegex = Regex("^4[0-9]{0,}\$")
    private val masterCardRegex = Regex("^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720)[0-9]{0,}\$")
    private val maestroRegex = Regex("^(5[06789]|6)[0-9]{0,}\$")
    private val discoverRegex =
        Regex("^(6011|65|64[4-9]|62212[6-9]|6221[3-9]|622[2-8]|6229[01]|62292[0-5])[0-9]{0,}\$")

    override suspend fun execute(
        intent: IdentifyBankCardIntent
    ): BankCardIdentifiedResult {
        val cardNumber = intent.number

        val trimmedCardNumber = intent.number.replace(" ", "")

        val type = when {
            trimmedCardNumber.matches(jcbRegex) -> BankCardType.JCB
            trimmedCardNumber.matches(visaRegex) -> BankCardType.VISA
            trimmedCardNumber.matches(masterCardRegex) -> BankCardType.MASTERCARD
            trimmedCardNumber.matches(discoverRegex) -> BankCardType.DISCOVER
            trimmedCardNumber.matches(maestroRegex) -> if (cardNumber[0] == '5') {
                BankCardType.MASTERCARD
            } else {
                BankCardType.MAESTRO
            }

            else -> BankCardType.UNKNOWN
        }

        return BankCardIdentifiedResult(type)
    }
}

data class IdentifyBankCardIntent(
    val number: String
) : ActionIntent

data class BankCardIdentifiedResult(
    val type: BankCardType
) : IntentResult
