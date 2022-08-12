package com.aa.mvivm.main.vm

import com.aa.mvivm.mvi.vm.Action

//should only be objects
sealed class MainAction : Action() {
    object IncreaseCounterClicked : MainAction()
    object ResetCounterClicked : MainAction()
}