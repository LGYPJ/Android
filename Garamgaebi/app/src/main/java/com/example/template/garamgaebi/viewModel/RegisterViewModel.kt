package com.example.template.garamgaebi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.garamgaebi.repository.RegisterRepository

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val email = MutableLiveData<String>()
    init {
        email.value = ""
    }
}