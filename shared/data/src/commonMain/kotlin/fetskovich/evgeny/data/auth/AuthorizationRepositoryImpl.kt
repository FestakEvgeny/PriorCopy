package fetskovich.evgeny.data.auth

import fetskovich.evgeny.domain.authorization.AuthorizationRepository
import fetskovich.evgeny.entity.auth.UserCredentials

class AuthorizationRepositoryImpl () : AuthorizationRepository {

    override suspend fun authorize(
        email: String,
        password: String
    ): Result<UserCredentials> {
        return Result.success(
            UserCredentials(
                id = "123",
                email = email,
                userName = "Festak",
            )
        )
    }
}