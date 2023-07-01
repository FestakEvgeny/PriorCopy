package fetskovich.evgeny.data.bank.mapper

import fetskovich.evgeny.data.database.BankCardModel
import fetskovich.evgeny.entity.card.BankCard
import fetskovich.evgeny.entity.card.BankCardType

class BankCardMapper {

    fun mapBankCardModelToEntity(model: BankCardModel): BankCard {
        return BankCard(
            id = model.id,
            bankCardType = BankCardType.valueOf(model.bankCardType),
            cardNumber = model.cardNumber,
            expirationDate = model.expirationDate,
            cvv = model.cvv
        )
    }

    fun mapBankCardEntityToModel(entity: BankCard) : BankCardModel {
        return BankCardModel(
            id = entity.id,
            bankCardType = entity.bankCardType.name,
            cardNumber = entity.cardNumber,
            expirationDate = entity.expirationDate,
            cvv = entity.cvv,
        )
    }
}