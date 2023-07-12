package fetskovich.evgeny.networking.ktor.setup.timeout

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class KtorTimeout(
    val request: Duration? = 60.seconds,
    val connect: Duration? = 60.seconds,
    val socket: Duration? = 60.seconds,
)
