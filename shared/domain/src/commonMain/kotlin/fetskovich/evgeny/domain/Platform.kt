package fetskovich.evgeny.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform