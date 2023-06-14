package fetskovich.evgeny.shared.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform