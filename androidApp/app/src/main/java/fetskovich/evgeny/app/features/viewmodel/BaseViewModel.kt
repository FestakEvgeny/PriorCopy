package fetskovich.evgeny.app.features.viewmodel

import androidx.lifecycle.ViewModel
import fetskovich.evgeny.architecture.mvi.ActionIntent

abstract class BaseViewModel : ViewModel() {

    abstract fun processIntent(
        intent: ActionIntent
    )
}