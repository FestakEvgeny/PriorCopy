package fetskovich.evgeny.architecture.usecase

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult

interface UseCase {

    suspend fun execute(
        intent: ActionIntent
    ): Result<IntentResult>
}
