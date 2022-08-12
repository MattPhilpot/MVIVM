package com.aa.mvivm.mvi.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<S: State>(defaultState: S) : ViewModel() {

    private val _state = MutableStateFlow(defaultState)
    val state = _state.asStateFlow()

    private val _command = MutableSharedFlow<Command>()
    val command = _command.asSharedFlow()

    /**
     * notify the UI of a change in model state
     */
    protected fun dispatchState(state: S) {
        _state.value = state
    }

    /**
     * Used to tell the UI to perform a one-time action
     */
    protected suspend fun dispatchCommand(command: Command) {
        _command.emit(command)
    }

    /**
     * Used by the UI when an action is performed, like clicking a button
     */
    fun dispatchAction(action: Action) {
        viewModelScope.launch { handleAction(action) }
    }

    /**
     * Handles all Actions triggered by the View
     */
    protected abstract suspend fun handleAction(action: Action)
}
