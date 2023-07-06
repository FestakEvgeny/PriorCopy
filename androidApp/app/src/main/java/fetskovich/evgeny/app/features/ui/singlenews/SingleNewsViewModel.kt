package fetskovich.evgeny.app.features.ui.singlenews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fetskovich.evgeny.app.features.ui.singlenews.mvi.SingleNewsScreenMviHandler
import fetskovich.evgeny.architecture.coroutines.contextprovider.CoroutinesContextProvider
import fetskovich.evgeny.domain.usecase.news.GetSingleNewsIntent
import fetskovich.evgeny.domain.usecase.news.GetSingleNewsUseCase
import kotlinx.coroutines.launch

class SingleNewsViewModel(
    newsId: String,
    private val mviHandler: SingleNewsScreenMviHandler,
    private val getSingleNewsUseCase: GetSingleNewsUseCase,
    private val coroutinesContextProvider: CoroutinesContextProvider,
) : ViewModel() {

    val stateFlow = mviHandler.stateFlow

    init {
        observeSingleNews(
            id = newsId,
        )
    }

    private fun observeSingleNews(id: String) {
        viewModelScope.launch(coroutinesContextProvider.io) {
            val result = getSingleNewsUseCase.execute(GetSingleNewsIntent(id))
            mviHandler.updateNewsReceivedData(
                title = result.news.title,
                htmlDescription = result.news.htmlDescription,
            )
        }
    }
}