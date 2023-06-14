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

    compileOptions {
        sourceCompatibility = AndroidBuildVersions.javaVersion
        targetCompatibility = AndroidBuildVersions.javaVersion
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
}

dependencies {
    implementation(project(":shared:architecture"))
    implementation(AndroidComposeLibrary.navigation)
}