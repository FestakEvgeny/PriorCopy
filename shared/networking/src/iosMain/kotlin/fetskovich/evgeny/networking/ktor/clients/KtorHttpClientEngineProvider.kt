package fetskovich.evgeny.networking.ktor.clients

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClientEngine() : HttpClientEngine {
    return Darwin.create()
}