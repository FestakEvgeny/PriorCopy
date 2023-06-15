package fetskovich.evgeny.domain.authorization

import fetskovich.evgeny.entity.auth.UserCredentials

interface AuthorizationRepository {

    suspend fun getLatestEmail(): Result<String?>
    suspend fun storeEmailAsLatest(email: String): Result<Boolean>

    suspend fun authorize(
        email: String,
        password: String,
    ): Result<UserCredentials>
}
