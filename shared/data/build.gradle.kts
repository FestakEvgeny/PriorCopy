plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:entity"))
                implementation(project(":shared:domain"))
                implementation(project(":shared:architecture"))
                implementation(project(":shared:networking"))

                implementation(shared.SharedLibrary.Kodein.core)

                implementation(shared.SharedLibrary.Coroutines.core)
                implementation(shared.SharedLibrary.Storage.settings)

                implementation(shared.SharedLibrary.SqlDelight.runtime)
                implementation(shared.SharedLibrary.SqlDelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(shared.SharedLibrary.SqlDelight.Android.driver)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(shared.SharedLibrary.SqlDelight.Ios.driver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "fetskovich.evgeny.data"
    compileSdk = AndroidBuildVersions.compileSdk
    defaultConfig {
        minSdk = AndroidBuildVersions.minSdk
        targetSdk = AndroidBuildVersions.targetSdk
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "fetskovich.evgeny.data.database"
    }
}