package fetskovich.evgeny.app

import android.app.Application
import android.content.Context
import fetskovich.evgeny.app.di.coreDiModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

class BaseApplication : Application(), DIAware {

    override val di: DI = DI {
        bind<Context>() with singleton { this@BaseApplication }

        import(coreDiModule)
    }
}