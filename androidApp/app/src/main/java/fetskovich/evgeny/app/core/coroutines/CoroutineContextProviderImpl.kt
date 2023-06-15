package fetskovich.evgeny.app.core.coroutines

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl(
    override val main: CoroutineDispatcher = Dispatchers.Main,
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val default: CoroutineContext = Dispatchers.Default,
    override val mainImmediate: CoroutineContext = Dispatchers.Main.immediate
) : CoroutinesContextProvider