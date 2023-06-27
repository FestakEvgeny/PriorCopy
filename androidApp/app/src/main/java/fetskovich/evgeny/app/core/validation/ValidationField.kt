package fetskovich.evgeny.app.core.validation

sealed class ValidationFieldState {

    abstract val text: String

    // Error not displayed, validation haven't executed
    data class Initial(
        val isOptional: Boolean  = false
    ) : ValidationFieldState() {
        override val text: String = ""
    }

    data class Valid(
        override val text: String
    ) : ValidationFieldState()

    data class Invalid(
        override val text: String,
        val errorMessage: String
    ) : ValidationFieldState()

    fun isAllowedToUpdate(
        incomingText: String
    ): Boolean {
        return !(this is Initial && incomingText.isEmpty())
    }
}
