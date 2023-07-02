package fetskovich.evgeny.app.di

import fetskovich.evgeny.app.core.coroutines.CoroutineContextProviderImpl
import fetskovich.evgeny.app.core.currency.CoreCurrencyFormatter
import fetskovich.evgeny.app.core.currency.CurrencyFormatter
import fetskovich.evgeny.app.core.resources.ResourceProvider
import fetskovich.evgeny.app.core.resources.ResourceProviderImpl
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val coreDiModule = DI.Module("Core") {
    bind<ResourceProvider>() with singleton {
        ResourceProviderImpl(context = instance())
    }

    bind<CoroutinesContextProvider>() with singleton { CoroutineContextProviderImpl() }

    bind<CurrencyFormatter>(CoreCurrencyFormatter.DI_TAG) with singleton { CoreCurrencyFormatter() }
}
