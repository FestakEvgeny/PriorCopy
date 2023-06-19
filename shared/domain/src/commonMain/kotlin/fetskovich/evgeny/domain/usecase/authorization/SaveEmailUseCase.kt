package fetskovich.evgeny.domain.usecase.authorization

import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.architecture.usecase.UseCase
import fetskovich.evgeny.domain.user.UserSettingsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveEmailUseCase(
    private val settingsStorage: UserSettingsStorage,
) : UseCase<SaveEmailIntent, SaveEmailResult> {

    override suspend fun execute(
        intent: SaveEmailIntent
    ): SaveEmailResult {
        settingsStorage.storeEmail(intent.email)
       return SaveEmailResult.Saved
    }
}

data class SaveEmailIntent(
    val email: String
) : ActionIntent

sealed class SaveEmailResult : IntentResult {

    object Saved : SaveEmailResult()
}
