package fetskovich.evgeny.networking.ktor.setup.logging

import io.ktor.client.plugins.logging.LogLevel as KLogLevel

enum class LogLevel {
    ALL,
    HEADERS,
    BODY,
    INFO,
    NONE,
}

internal fun LogLevel.toKtor() = when(this) {
    LogLevel.ALL -> KLogLevel.ALL
    LogLevel.HEADERS -> KLogLevel.HEADERS
    LogLevel.BODY -> KLogLevel.BODY
    LogLevel.INFO -> KLogLevel.INFO
    LogLevel.NONE -> KLogLevel.NONE
}