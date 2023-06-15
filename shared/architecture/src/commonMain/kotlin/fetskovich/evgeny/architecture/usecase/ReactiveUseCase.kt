package fetskovich.evgeny.architecture.usecase

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import kotlinx.coroutines.flow.Flow

interface ReactiveUseCase<I : ActionIntent, R : IntentResult> {

    fun execute(
        intent: I
    ): Flow<R>
}
