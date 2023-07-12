package fetskovich.evgeny.networking.ktor.setup.json

import kotlinx.serialization.json.Json

internal val KtorJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
}
