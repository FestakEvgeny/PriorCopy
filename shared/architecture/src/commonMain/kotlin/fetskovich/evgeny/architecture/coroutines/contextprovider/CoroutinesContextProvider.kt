package fetskovich.evgeny.architecture.coroutines.contextprovider

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

interface CoroutinesContextProvider {
    val main: CoroutineContext
    val mainImmediate: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}