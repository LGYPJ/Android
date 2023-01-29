package com.softsquared.template.Garamgaebi.src.seminar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeminarButtonViewModel: ViewModel() {

    private val _button = MutableLiveData<String>()
    val button: LiveData<String>
    get() = _button

    fun updateValue(button: String){
        _button.value = "신청완료"
    }
}