import android.AndroidComposeLibrary

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = AndroidBuildVersions.compileSdk


    defaultConfig {
        minSdk = AndroidBuildVersions.minSdk
        targetSdk = AndroidBuildVersions.targetSdk
    }

    kotlinOptions {
        jvmTarget = AndroidBuildVersions.jvmTarget
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidComposeLibrary.composeCompilerVersion
    }
    namespace = "fetskovich.evgeny.navigation"
}

kotlin{
    jvmToolchain(17)
}

dependencies {
    implementation(project(":shared:architecture"))
    implementation(AndroidComposeLibrary.navigation)
}