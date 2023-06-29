package fetskovich.evgeny.app

import android.app.Application
import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import fetskovich.evgeny.app.di.coreDiModule
import fetskovich.evgeny.data.di.dataDiModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

val ApplicationModuleComposition = staticCompositionLocalOf {
    DI {}
}

class BaseApplication : Application(), DIAware {

    override val di: DI = DI {
        bind<Context>() with singleton { this@BaseApplication }

        import(coreDiModule)
        import(dataDiModule)
    }
}
