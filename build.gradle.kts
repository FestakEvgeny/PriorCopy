plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0").apply(false)
    id("com.android.library").version("8.1.0").apply(false)
    kotlin("android").version("1.9.10").apply(false)
    kotlin("multiplatform").version("1.9.10").apply(false)
    kotlin("jvm").version("1.9.10").apply(false)
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}


buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
