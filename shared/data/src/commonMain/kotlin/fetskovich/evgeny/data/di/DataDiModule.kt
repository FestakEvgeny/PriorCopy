package fetskovich.evgeny.data.di

import fetskovich.evgeny.data.auth.AuthorizationRepositoryImpl
import fetskovich.evgeny.data.bank.BankCardRepositoryImpl
import fetskovich.evgeny.data.bank.mapper.BankCardMapper
import fetskovich.evgeny.data.database.Database
import fetskovich.evgeny.data.database.dao.BankCardDao
import fetskovich.evgeny.data.exchange.ExchangeRateRepositoryImpl
import fetskovich.evgeny.data.user.UserSettingsStorageImpl
import fetskovich.evgeny.domain.authorization.AuthorizationRepository
import fetskovich.evgeny.domain.bank.BankCardRepository
import fetskovich.evgeny.domain.exchange.ExchangeRateRepository
import fetskovich.evgeny.domain.user.UserSettingsStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

// Later separate per different modules
val dataDiModule = DI.Module("DataModule") {
    // Database
    bind<Database>() with singleton {
        Database(
            databaseDriverFactory = instance(),
            coroutinesContextProvider = instance(),
        )
    }

    bind<BankCardDao>() with singleton {
        val database: Database = instance()
        database.bankCardDao
    }


    // Mappers
    bind<BankCardMapper>() with provider { BankCardMapper() }

    // repositories (separate to another module if there will be a lot)
    bind<AuthorizationRepository>() with singleton { AuthorizationRepositoryImpl() }
    bind<BankCardRepository>() with singleton {
        BankCardRepositoryImpl(
            bankCardDao = instance(),
            bankCardMapper = instance(),
        )
    }
    bind<ExchangeRateRepository>() with singleton {
        ExchangeRateRepositoryImpl(
            api = instance(),
            coroutinesContextProvider = instance(),
        )
    }

    // Some preference and settings
    bind<UserSettingsStorage>() with singleton { UserSettingsStorageImpl() }
}