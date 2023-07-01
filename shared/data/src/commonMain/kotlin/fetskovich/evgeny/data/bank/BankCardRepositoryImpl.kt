package fetskovich.evgeny.data.bank

import fetskovich.evgeny.data.bank.mapper.BankCardMapper
import fetskovich.evgeny.data.database.dao.BankCardDao
import fetskovich.evgeny.domain.bank.BankCardRepository
import fetskovich.evgeny.entity.card.BankCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BankCardRepositoryImpl(
    private val bankCardDao: BankCardDao,
    private val bankCardMapper: BankCardMapper,
) : BankCardRepository {

    override suspend fun createBankCard(card: BankCard) {
        val model = bankCardMapper.mapBankCardEntityToModel(card)
        bankCardDao.createBankCardModel(model)
    }

    override fun getBankCards(): Flow<List<BankCard>> {
        return bankCardDao.observeBankCardModels()
            .map {
                it.map(bankCardMapper::mapBankCardModelToEntity)
            }
    }
}