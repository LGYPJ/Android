package com.softsquared.template.Garamgaebi.src.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeminarButtonViewModel: ViewModel() {

    private val _button = MutableLiveData<String>()
    val button: LiveData<String> = _button

    private val _button1 = MutableLiveData<Int>()
    val button1: LiveData<Int> = _button1

    private val _button2 = MutableLiveData<Boolean>()
    val button2: LiveData<Boolean> = _button2

    fun updateValue(tmp: String) {
        _button.value = tmp
    }

    fun updateValue1(tmp1: Int) {
        _button1.value = tmp1
    }

    fun updateValue2(tmp2: Boolean) {
        _button2.value = tmp2
    }

}