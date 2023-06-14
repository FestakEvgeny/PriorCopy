package android

object AndroidComposeLibrary {

    const val composeCompilerVersion = "1.4.4"
    const val composeVersion = "1.4.0"
    private const val composeActivityVersion = "1.7.0"

    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val navigation = "androidx.navigation:navigation-compose:2.5.3"

    const val animation = "androidx.compose.animation:animation:$composeVersion"

    object Ui {
        const val activity = "androidx.activity:activity-compose:$composeActivityVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"

        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        const val material = "androidx.compose.material:material:$composeVersion"
        const val icons = "androidx.compose.material:material-icons-extended:$composeVersion"

        const val util = "androidx.compose.ui:ui-util:$composeVersion"
    }
}