package fetskovich.evgeny.entity

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform