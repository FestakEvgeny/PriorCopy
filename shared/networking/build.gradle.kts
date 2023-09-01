import shared.SharedLibrary

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

apply(plugin = "kotlinx-serialization")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    jvmToolchain(17)

    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = AndroidBuildVersions.jvmTarget
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "networking"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:entity"))
                implementation(SharedLibrary.Coroutines.core)

                implementation(SharedLibrary.Ktor.CORE)
                implementation(SharedLibrary.Ktor.JSON)
                implementation(SharedLibrary.Ktor.UTILS)
                implementation(SharedLibrary.Ktor.LOGGING_PLUGIN)
                implementation(SharedLibrary.Ktor.CONTENT_NEGOTIATION_PLUGIN)
                implementation(SharedLibrary.Ktor.AUTH_PLUGIN)

                implementation(SharedLibrary.Kotlin.SERIALIZATION)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(shared.SharedLibrary.Ktor.Android.OK_HTTP)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(shared.SharedLibrary.Ktor.Ios.DARWIN)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "fetskovich.evgeny.networking"
    compileSdk = AndroidBuildVersions.compileSdk
    defaultConfig {
        minSdk = AndroidBuildVersions.minSdk
    }
}