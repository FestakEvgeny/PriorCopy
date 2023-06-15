package android

object AndroidComposeLibrary {

    const val composeCompilerVersion = "1.4.7"
    const val composeVersion = "1.4.3"
    private const val composeActivityVersion = "1.7.0"

    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val navigation = "androidx.navigation:navigation-compose:2.5.3"

    const val animation = "androidx.compose.animation:animation:$composeVersion"

    object Ui {
        const val activity = "androidx.activity:activity-compose:$composeActivityVersion"

        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        const val material = "androidx.compose.material:material:$composeVersion"
        const val icons = "androidx.compose.material:material-icons-extended:$composeVersion"

        const val util = "androidx.compose.ui:ui-util:$composeVersion"
    }

    object Lifecycle {
        private const val version = "2.6.1"

        const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        const val runtimeLifecycle  = "androidx.lifecycle:lifecycle-runtime-compose:$version"
    }
}