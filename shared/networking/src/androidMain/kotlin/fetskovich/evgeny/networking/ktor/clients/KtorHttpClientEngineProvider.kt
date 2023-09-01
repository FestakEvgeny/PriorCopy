package fetskovich.evgeny.networking.ktor.clients

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpClientEngine() : HttpClientEngine {
    return OkHttp.create()
}