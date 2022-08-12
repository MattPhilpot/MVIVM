package com.aa.mvivm.mvi.view

import com.aa.mvivm.mvi.vm.Command
import com.aa.mvivm.mvi.vm.State

interface MviView {
    fun handleState(state: State) { } //don't need to override
    fun handleCommand(command: Command)
}
