package com.aa.mvivm.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.aa.mvivm.R
import com.aa.mvivm.databinding.ActivityMainBinding
import com.aa.mvivm.main.vm.MainCommand
import com.aa.mvivm.main.vm.MainViewModel
import com.aa.mvivm.mvi.view.MviActivity
import com.aa.mvivm.mvi.vm.Command
import com.aa.mvivm.mvi.vm.MviViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : MviActivity() {

    private val viewmodel by viewModels<MainViewModel>()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //must call binding inside onCreate to setContentView
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
    }

    override fun onRegisterViewModels(): List<MviViewModel<*>> = listOf(viewmodel)

    override fun handleCommand(command: Command) {
        when(command) {
            is MainCommand.DisplayToast -> {
                Toast.makeText(this, command.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
