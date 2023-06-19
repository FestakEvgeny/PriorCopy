package fetskovich.evgeny.domain.user

interface UserSettingsStorage {

    suspend fun getEmail(): String?
    suspend fun storeEmail(email: String?)
}
