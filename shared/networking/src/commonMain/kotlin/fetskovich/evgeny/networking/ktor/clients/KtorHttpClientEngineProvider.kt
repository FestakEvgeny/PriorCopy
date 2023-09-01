package fetskovich.evgeny.networking.ktor.clients

import io.ktor.client.engine.HttpClientEngine

expect fun provideHttpClientEngine() : HttpClientEngine