package fetskovich.evgeny.domain.auth

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.authorization.AuthorizationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

object GetLatestEmailIntent : ActionIntent

sealed class GetLatestEmailResult : IntentResult {

    object Loading : GetLatestEmailResult()

    data class Loaded(
        val email: String
    ) : GetLatestEmailResult()

    object NotExists : GetLatestEmailResult()

    data class Error(val throwable: Throwable) : GetLatestEmailResult()
}

class GetLatestEmailUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ReactiveUseCase<GetLatestEmailIntent, GetLatestEmailResult> {

    override fun execute(
        intent: GetLatestEmailIntent
    ): Flow<GetLatestEmailResult> = flow {
        val latestEmailResult = authorizationRepository.getLatestEmail()

        val result = latestEmailResult.fold(
            onSuccess = { email ->
                if (email.isNullOrEmpty()) {
                    GetLatestEmailResult.NotExists
                } else {
                    GetLatestEmailResult.Loaded(email)
                }
            },
            onFailure = { error ->
                GetLatestEmailResult.Error(error)
            }
        )

        emit(result)
    }.onStart {
        emit(GetLatestEmailResult.Loading)
    }.flowOn(coroutinesContextProvider.io)
}

