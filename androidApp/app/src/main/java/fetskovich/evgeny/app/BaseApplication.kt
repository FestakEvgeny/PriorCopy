package fetskovich.evgeny.app

import android.app.Application
import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import fetskovich.evgeny.app.di.coreDiModule
import fetskovich.evgeny.data.database.DatabaseDriverFactory
import fetskovich.evgeny.data.di.dataDiModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val LocalApplicationModuleComposition = staticCompositionLocalOf {
    DI {}
}

class BaseApplication : Application(), DIAware {

    override val di: DI = DI {
        bind<Context>() with singleton { this@BaseApplication }

        bind<DatabaseDriverFactory>() with singleton {
            DatabaseDriverFactory(
                context = instance(),
            )
        }

        import(coreDiModule)
        import(dataDiModule)
    }
}
