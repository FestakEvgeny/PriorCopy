package fetskovich.evgeny.architecture.usecase

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult

interface UseCase<I : ActionIntent, R : IntentResult> {

    suspend fun execute(
        intent: I
    ): R
}
