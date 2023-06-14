package shared

object SharedLibrary {

    object Coroutines {

        private const val coroutinesVersion = "1.6.4"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }
}