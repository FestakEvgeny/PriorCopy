package fetskovich.evgeny.domain.usecase.news

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.ReactiveUseCase
import fetskovich.evgeny.entity.news.BankNewsShortcut
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsUseCase : ReactiveUseCase<GetNewsIntent, GetNewsResult> {

    override fun execute(
        intent: GetNewsIntent
    ): Flow<GetNewsResult> = flow {
        val list = listOf(
            BankNewsShortcut("1", "Будьте бдительны"),
            BankNewsShortcut("2", "Активируйте свой кредит"),
            BankNewsShortcut("3", "Обновление программы Приведи друга"),
            BankNewsShortcut("4", "Летнее предложение"),
            BankNewsShortcut("5", "Приорбанк в соцсетях"),
        )

        emit(GetNewsResult(list))
    }
}

object GetNewsIntent : ActionIntent

data class GetNewsResult(
    val news: List<BankNewsShortcut>
) : IntentResult

