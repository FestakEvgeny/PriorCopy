package fetskovich.evgeny.domain.usecase.authorization

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.domain.user.UserSettingsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class GetEmailUseCase(
    private val userSettingsStorage: UserSettingsStorage,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ReactiveUseCase<GetEmailIntent, GetEmailResult> {

    override fun execute(
        intent: GetEmailIntent
    ): Flow<GetEmailResult> = flow {
        val latestEmailResult = userSettingsStorage.getEmail()
        val result = if (latestEmailResult != null) {
            GetEmailResult.Loaded(latestEmailResult)
        } else {
            GetEmailResult.NotExists
        }
        emit(result)

    }.onStart {
        emit(GetEmailResult.Loading)
    }.flowOn(coroutinesContextProvider.io)
}

object GetEmailIntent : ActionIntent

sealed class GetEmailResult : IntentResult {

    object Loading : GetEmailResult()

    data class Loaded(
        val email: String
    ) : GetEmailResult()

    object NotExists : GetEmailResult()

    data class Error(val throwable: Throwable) : GetEmailResult()
}