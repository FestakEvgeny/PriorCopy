package shared

object SharedLibrary {

    object Kodein {

        private const val version = "7.20.1"
        const val core = "org.kodein.di:kodein-di:$version"
    }

    object Coroutines {

        private const val coroutinesVersion = "1.7.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Storage {

        const val settings = "com.russhwolf:multiplatform-settings-no-arg:1.0.0"
    }
}