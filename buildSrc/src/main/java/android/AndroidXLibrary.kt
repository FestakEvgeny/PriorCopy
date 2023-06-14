package android

object AndroidXLibrary {

    private const val coreKtxVersion = "1.6.0"
    const val core = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.3.0"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val exifVersion = "1.3.6"
    const val exif = "androidx.exifinterface:exifinterface:$exifVersion"

    private const val workManagerVersion = "2.8.1"
    const val workmanager = "androidx.work:work-runtime-ktx:$workManagerVersion"

    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"

    const val material = "com.google.android.material:material:1.6.1"
}