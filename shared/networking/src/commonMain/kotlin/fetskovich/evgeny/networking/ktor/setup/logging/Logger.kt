package fetskovich.evgeny.networking.ktor.setup.logging

import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.logging.Logger as KLogger

enum class Logger {
    DEFAULT,
    SIMPLE,
    EMPTY,
}

internal fun Logger.toKtor() = when(this) {
    Logger.DEFAULT -> KLogger.DEFAULT
    Logger.SIMPLE -> KLogger.SIMPLE
    Logger.EMPTY -> KLogger.EMPTY
}