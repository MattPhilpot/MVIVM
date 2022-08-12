package com.aa.mvivm.main.vm

import androidx.lifecycle.viewModelScope
import com.aa.mvivm.mvi.vm.Action
import com.aa.mvivm.mvi.vm.MviViewModel
import kotlinx.coroutines.launch

class MainViewModel : MviViewModel<MainState>(MainState.Default) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is MainAction.IncreaseCounterClicked -> onIncreaseCounterClicked() //can either call method
            is MainAction.ResetCounterClicked -> {
                //or handle logic here
                dispatchState(MainState.DoingSomething(0))
                sendToastCommand("Resetting to 0")
            }
        }
    }

    private fun onIncreaseCounterClicked() {
        val next = ((state.value as? MainState.DoingSomething)?.counter ?: 0) + 1
        dispatchState(MainState.DoingSomething(next))

        if (next.mod(10) == 0) {
            sendToastCommand("You've clicked $next times")
        }
    }

    private fun sendToastCommand(message: String) = viewModelScope.launch {
        dispatchCommand(MainCommand.DisplayToast(message))
    }
}