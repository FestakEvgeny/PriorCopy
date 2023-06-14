package fetskovich.evgeny.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform