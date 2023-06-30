package shared

object SharedLibrary {

    object Core {
        val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
    }

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

    object SqlDelight  {

        // also must be changed at build.gradle.kts root file
        private const val version = "1.5.5"
        const val plugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val runtime = "com.squareup.sqldelight:runtime:$version"

        object Android {
            const val driver = "com.squareup.sqldelight:android-driver:$version"
        }

        object Ios {
            const val driver = "com.squareup.sqldelight:native-driver:$version"
        }
    }
}