pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RecipesKmm"
include(":androidApp:app")
include(":androidApp:navigation")
include(":androidApp:presentation:theme")
include(":androidApp:presentation:components")

// Shared
include(":shared:architecture")
include(":shared:domain")
include(":shared:data")
include(":shared:entity")
include(":shared:core")
include(":shared:networking")
