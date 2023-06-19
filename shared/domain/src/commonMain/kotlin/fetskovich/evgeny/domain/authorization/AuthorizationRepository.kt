package fetskovich.evgeny.domain.authorization

import fetskovich.evgeny.entity.auth.UserCredentials

interface AuthorizationRepository {

    suspend fun authorize(
        email: String,
        password: String,
    ): Result<UserCredentials>
}
