package fetskovich.evgeny.domain.usecase.authorization

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.authorization.AuthorizationRepository
import fetskovich.evgeny.entity.auth.UserCredentials
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthorizeUserUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val saveEmailUseCase: SaveEmailUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ReactiveUseCase<AuthorizeUserIntent, AuthorizeUserResult> {

    override fun execute(
        intent: AuthorizeUserIntent
    ): Flow<AuthorizeUserResult> = flow {
        authorizationRepository.authorize(
            email = intent.email,
            password = intent.password,
        ).fold(
            onSuccess = { data ->
                saveEmailUseCase.execute(SaveEmailIntent(data.email))
                emit(AuthorizeUserResult.Authorized(data))
            },
            onFailure = { error ->
                emit(AuthorizeUserResult.Error(error))
            }
        )
    }.flowOn(coroutinesContextProvider.io)
}

data class AuthorizeUserIntent(
    val email: String,
    val password: String,
) : ActionIntent

sealed class AuthorizeUserResult : IntentResult {

    data class Authorized(
        private val credentials: UserCredentials,
    ) : AuthorizeUserResult()

    data class Error(
        val error: Throwable
    ) : AuthorizeUserResult()

    object Loading : AuthorizeUserResult()
}
