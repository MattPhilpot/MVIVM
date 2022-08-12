package com.aa.mvivm.mvi.view

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.aa.mvivm.mvi.vm.MviViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MviViewRegistry(private val view: MviView, private val lifecycleOwner: LifecycleOwner) :
    DefaultLifecycleObserver {

    private val viewModelCache: MutableMap<String, MviViewModel<*>> = mutableMapOf()
    private val jobList: MutableList<Job> = mutableListOf()

    fun register(viewModelToRegister: List<MviViewModel<*>>) {
        check(lifecycleOwner.lifecycle.currentState <= Lifecycle.State.CREATED) { "Lifecycle state must be before or on CREATED." }
        lifecycleOwner.lifecycle.removeObserver(this)
        viewModelCache.clear()
        viewModelToRegister.forEach { viewModelCache[it.javaClass.simpleName] = it }
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (viewModelCache.isEmpty()) { return }

        val scope = lifecycleOwner.lifecycleScope
        viewModelCache.values.forEach {

            jobList.add(scope.launch {
                it.state.collect { state -> view.handleState(state) }
            })

            jobList.add(scope.launch {
                it.command.collect { command -> view.handleCommand(command) }
            })
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        jobList.forEach { it.cancel() }
        jobList.clear()
        super.onStop(owner)
    }
}
