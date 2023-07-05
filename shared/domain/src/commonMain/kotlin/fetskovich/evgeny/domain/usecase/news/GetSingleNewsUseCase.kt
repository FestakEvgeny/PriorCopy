package fetskovich.evgeny.domain.usecase.news

import fetskovich.evgeny.architecture.mvi.ActionIntent
import fetskovich.evgeny.architecture.mvi.IntentResult
import fetskovich.evgeny.architecture.usecase.UseCase
import fetskovich.evgeny.entity.news.BankNewsFull
import kotlinx.coroutines.flow.first

private const val HTML_COMMON_DESCRIPTION =
    "Чтобы не стать жертвой мошенников, просим соблюдать важные правила</br></br>" +
            "1. <b> Если вам звонят якобы от лица</b> сотрудника банка или правоохранительных органов, особенно на мессенджер (например Viber)" +
            ", <b>сообщают о мошенничестве</b> либо <b>просят принять участие в расследовании или спецоперации</b>, запрашивают персональную информацию (в том числе M-коды), " +
            " <b>не продолжайте разговор</b>. Скорее всего это мошенники." +
            "3. <b> Если вам звонят якобы от лица</b> сотрудника банка или правоохранительных органов, особенно на мессенджер (например Viber)" +
            ", <b>сообщают о мошенничестве</b> либо <b>просят принять участие в расследовании или спецоперации</b>, запрашивают персональную информацию (в том числе M-коды), " +
            " <b>не продолжайте разговор</b>. Скорее всего это мошенники." +
            "4. <b> Если вам звонят якобы от лица</b> сотрудника банка или правоохранительных органов, особенно на мессенджер (например Viber)" +
            ", <b>сообщают о мошенничестве</b> либо <b>просят принять участие в расследовании или спецоперации</b>, запрашивают персональную информацию (в том числе M-коды), " +
            " <b>не продолжайте разговор</b>. Скорее всего это мошенники." +
            "5. <b> Если вам звонят якобы от лица</b> сотрудника банка или правоохранительных органов, особенно на мессенджер (например Viber)" +
            ", <b>сообщают о мошенничестве</b> либо <b>просят принять участие в расследовании или спецоперации</b>, запрашивают персональную информацию (в том числе M-коды), " +
            " <b>не продолжайте разговор</b>. Скорее всего это мошенники." +
            "6. <b> Если вам звонят якобы от лица</b> сотрудника банка или правоохранительных органов, особенно на мессенджер (например Viber)" +
            ", <b>сообщают о мошенничестве</b> либо <b>просят принять участие в расследовании или спецоперации</b>, запрашивают персональную информацию (в том числе M-коды), " +
            " <b>не продолжайте разговор</b>. Скорее всего это мошенники."

class GetSingleNewsUseCase(
    private val getNewsUseCase: GetNewsUseCase,
) : UseCase<GetSingleNewsIntent, GetSingleNewsResult> {

    override suspend fun execute(
        intent: GetSingleNewsIntent
    ): GetSingleNewsResult {
        val newsResult = getNewsUseCase.execute(GetNewsIntent).first()
        val singleNews = newsResult.news.first { it.id == intent.id }
        val fullNews = BankNewsFull(
            id = singleNews.id,
            title = singleNews.title,
            htmlDescription = HTML_COMMON_DESCRIPTION
        )

        return GetSingleNewsResult(
            news = fullNews,
        )
    }
}

data class GetSingleNewsIntent(
    val id: String,
) : ActionIntent

data class GetSingleNewsResult(
    val news: BankNewsFull
) : IntentResult

