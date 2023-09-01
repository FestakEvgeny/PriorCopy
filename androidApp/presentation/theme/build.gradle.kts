import android.AndroidComposeLibrary

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "fetskovich.evgeny.presentation.theme"
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
}

kotlin{
    jvmToolchain(17)
}

dependencies {
    implementation(AndroidComposeLibrary.Ui.ui)
    implementation(AndroidComposeLibrary.Ui.tooling)
    implementation(AndroidComposeLibrary.Ui.material)
}