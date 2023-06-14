package fetskovich.evgeny.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform