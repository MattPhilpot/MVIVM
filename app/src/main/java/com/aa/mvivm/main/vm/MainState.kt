package com.aa.mvivm.main.vm

import com.aa.mvivm.mvi.vm.State

sealed class MainState(val counter: Int) : State() {
    object Default : MainState(0)
    class DoingSomething(counter: Int) : MainState(counter)
}
