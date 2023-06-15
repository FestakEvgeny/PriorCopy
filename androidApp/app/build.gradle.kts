import android.AndroidComposeLibrary
import android.AndroidXLibrary

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "fetskovich.evgeny.recipeskmm.app"

    compileSdk = AndroidBuildVersions.compileSdk
    defaultConfig {
        applicationId = AndroidBuildVersions.applicationId
        minSdk = AndroidBuildVersions.minSdk
        targetSdk = AndroidBuildVersions.targetSdk
        versionCode = AndroidBuildVersions.versionCode
        versionName = AndroidBuildVersions.versionName
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    // Shared
    implementation(project(":shared:architecture"))
    implementation(project(":shared:entity"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))

    // Modules
    implementation(project(":androidApp:navigation"))
    implementation(project(":androidApp:presentation:theme"))
    implementation(project(":androidApp:presentation:components"))


    // Libraries
    implementation(AndroidXLibrary.core)
    implementation(AndroidXLibrary.appCompat)
    implementation(AndroidXLibrary.workmanager)
    implementation(AndroidXLibrary.viewModel)
    implementation(AndroidXLibrary.material)

    implementation(AndroidComposeLibrary.Ui.activity)
    implementation(AndroidComposeLibrary.Ui.viewModel)
    implementation(AndroidComposeLibrary.Ui.ui)
    implementation(AndroidComposeLibrary.Ui.tooling)
    implementation(AndroidComposeLibrary.Ui.toolingPreview)
    implementation(AndroidComposeLibrary.Ui.constraint)

    implementation(AndroidComposeLibrary.Ui.material)
    implementation(AndroidComposeLibrary.Ui.icons)

    implementation(AndroidComposeLibrary.Ui.util)
    implementation(AndroidComposeLibrary.runtime)
    implementation(AndroidComposeLibrary.animation)
    implementation(AndroidComposeLibrary.navigation)
}