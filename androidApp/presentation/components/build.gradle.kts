import android.AndroidComposeLibrary
import android.AndroidXLibrary

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
    implementation(project(":androidApp:presentation:theme"))

    implementation(AndroidXLibrary.core)
    implementation(AndroidXLibrary.appCompat)
    implementation(AndroidXLibrary.material)

    implementation(AndroidComposeLibrary.Ui.ui)
    implementation(AndroidComposeLibrary.Ui.tooling)
    implementation(AndroidComposeLibrary.Ui.toolingPreview)
    implementation(AndroidComposeLibrary.Ui.constraint)

    implementation(AndroidComposeLibrary.Ui.material)
    implementation(AndroidComposeLibrary.Ui.icons)

    implementation(AndroidComposeLibrary.Ui.util)
}