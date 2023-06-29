package fetskovich.evgeny.app.features.ui.addbankcard.mvi

import androidx.annotation.DrawableRes
import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.architecture.mvi.ScreenState
import fetskovich.evgeny.entity.card.BankCardType
import fetskovich.evgeny.recipeskmm.app.R

data class AddAnotherBankCardScreenState(
    val cardNumber: ValidationFieldState = ValidationFieldState.Initial(),
    val cardExpiration: ValidationFieldState = ValidationFieldState.Initial(),
    val cardCvv: ValidationFieldState = ValidationFieldState.Initial(),
    val cardVariant: BankCardVariant = BankCardVariant.UNKNOWN,
) : ScreenState

enum class BankCardVariant(@DrawableRes val imageId: Int) {
    JCB(R.drawable.ic_money),
    VISA(R.drawable.ic_profile),
    MASTERCARD(R.drawable.ic_message),
    DISCOVER(R.drawable.ic_money),
    MAESTRO(R.drawable.ic_money),
    UNKNOWN(R.drawable.ic_more);

    companion object {

        fun fromBankCardType(type: BankCardType): BankCardVariant {
            return when (type) {
                BankCardType.JCB -> JCB
                BankCardType.VISA -> VISA
                BankCardType.MASTERCARD -> MASTERCARD
                BankCardType.DISCOVER -> DISCOVER
                BankCardType.MAESTRO -> MAESTRO
                BankCardType.UNKNOWN -> UNKNOWN
            }
        }
    }
}
