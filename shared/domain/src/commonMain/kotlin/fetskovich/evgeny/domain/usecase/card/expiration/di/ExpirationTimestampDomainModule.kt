package fetskovich.evgeny.domain.usecase.card.expiration.di

import fetskovich.evgeny.domain.usecase.card.expiration.ExpirationDateToTimestampUseCase
import fetskovich.evgeny.domain.usecase.card.expiration.TimestampToExpirationDateUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val expirationTimestampDomainModule = DI.Module("ExpirationTimestamp") {
    bind<ExpirationDateToTimestampUseCase>() with provider { ExpirationDateToTimestampUseCase() }
    bind<TimestampToExpirationDateUseCase>() with provider { TimestampToExpirationDateUseCase() }
}
