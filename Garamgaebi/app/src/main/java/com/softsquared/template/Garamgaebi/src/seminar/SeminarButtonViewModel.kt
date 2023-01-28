package com.softsquared.template.Garamgaebi.src.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeminarButtonViewModel: ViewModel() {

    private val _button = MutableLiveData<Boolean>()
    val button: LiveData<Boolean>
    get() = _button

    init {
        _button.value = false
    }
    fun updateValue(button : Boolean){
        _button.value = button
    }
}