package com.aa.mvivm.mvi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aa.mvivm.mvi.vm.MviViewModel

abstract class MviActivity : AppCompatActivity(), MviView {

    private val viewRegistry: MviViewRegistry by lazy {
        MviViewRegistry(this, this)
    }

    protected abstract fun onRegisterViewModels(): List<MviViewModel<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelToRegister = onRegisterViewModels()
        viewRegistry.register(viewModelToRegister)
    }
}
