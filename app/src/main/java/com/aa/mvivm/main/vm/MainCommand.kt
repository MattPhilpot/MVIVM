package com.aa.mvivm.main.vm

import com.aa.mvivm.mvi.vm.Command

sealed class MainCommand : Command() {
    class DisplayToast(val message: String) : MainCommand()
}