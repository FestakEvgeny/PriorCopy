package fetskovich.evgeny.data.user

import com.russhwolf.settings.Settings
import fetskovich.evgeny.domain.user.UserSettingsStorage

private const val EMAIL_KEY = "email_key"

class UserSettingsStorageImpl : UserSettingsStorage {

    private val settings = Settings()

    override suspend fun getEmail(): String? {
        val email = settings.getString(EMAIL_KEY, "")
        println("GET EMAIL: $email")
        return email.ifEmpty { null }
    }

    override suspend fun storeEmail(email: String?) {
        settings.putString(EMAIL_KEY, email ?: "")
    }
}
