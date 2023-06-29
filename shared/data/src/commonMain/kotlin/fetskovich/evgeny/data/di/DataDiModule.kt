package fetskovich.evgeny.data.di

import fetskovich.evgeny.data.auth.AuthorizationRepositoryImpl
import fetskovich.evgeny.data.user.UserSettingsStorageImpl
import fetskovich.evgeny.domain.authorization.AuthorizationRepository
import fetskovich.evgeny.domain.user.UserSettingsStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val dataDiModule = DI.Module ("DataModule") {
    // repositories (separate to another module if there will be a lot)
    bind<AuthorizationRepository>() with singleton { AuthorizationRepositoryImpl() }

    // Some preference and settings
    bind<UserSettingsStorage>() with singleton { UserSettingsStorageImpl() }
}