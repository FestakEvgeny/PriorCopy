package fetskovich.evgeny.architecture

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform