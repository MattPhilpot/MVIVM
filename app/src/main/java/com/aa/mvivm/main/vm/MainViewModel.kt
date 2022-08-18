package com.aa.mvivm.main.vm

import androidx.lifecycle.viewModelScope
import com.aa.mvivm.main.repo.EntryRepo
import com.aa.mvivm.mvi.vm.Action
import com.aa.mvivm.mvi.vm.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val entryRepo: EntryRepo) : MviViewModel<MainState>(MainState.Default) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is MainAction.IncreaseCounterClicked -> onIncreaseCounterClicked() //can either call method
            is MainAction.ResetCounterClicked -> {
                //or handle logic here
                dispatchState(MainState.DoingSomething(0))
                sendToastCommand("Resetting to 0")
            }
            is MainAction.GetEntries -> doGetEntries()
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

    private fun doGetEntries() {
        viewModelScope.launch {
            //getEntries is a suspended method, must launch within coroutine scope
            entryRepo.getEntries()?.let {
                dispatchState(MainState.DoingSomething(it.entryCount))
                sendToastCommand("${it.entryCount} entries found")
            }
        }
    }
}