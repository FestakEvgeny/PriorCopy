import org.gradle.api.JavaVersion

private const val MAJOR_VERSION  = 0
private const val MINOR_VERSION = 0
private const val PATCH_VERSION = 1

object AndroidBuildVersions {

    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24

    const val versionCode = MAJOR_VERSION * 10000 + MINOR_VERSION + PATCH_VERSION
    const val versionName = "$MAJOR_VERSION.$MINOR_VERSION.$PATCH_VERSION"

    const val applicationId = "fetskovich.evgeny.recipeskmm"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}