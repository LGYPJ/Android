package com.example.template.garamgaebi.viewModel

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.RegisterEmailResponse
import com.example.template.garamgaebi.repository.RegisterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val email = MutableLiveData<String>("")
    val emailFocusing = MutableLiveData<Boolean>(false)
    val timerFirst = MutableLiveData<Boolean>(true)
    val isTimerRunning = MutableLiveData<Boolean>(false)
    val authNum = MutableLiveData<String>("")
    val isEmailValid = MutableLiveData<Boolean>(false)
    val isNumValid = MutableLiveData<Boolean>(false)
    val isCompleteAuth = MutableLiveData<Boolean>(false)

    private val _emailConfirm = MutableLiveData<RegisterEmailResponse>()
    val emailConfirm : LiveData<RegisterEmailResponse>
        get() = _emailConfirm

    var _timerCount = MutableLiveData<Int>(5)
    lateinit var timer : Job
    val timerCount : LiveData<Int>
        get() = _timerCount



    fun postEmailConfirm(email : String) {
        viewModelScope.launch {
            val response = registerRepository.postEmailConfirm(email)
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _emailConfirm.postValue(response.body())
                Log.d("postEmailConfirm", "${response.body()}")
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    fun timerStart() {
        if(::timer.isInitialized) timer.cancel()
        isTimerRunning.value = true
        _timerCount.value = 5
        timerFirst.value = false
        timer = viewModelScope.launch {
            while(_timerCount.value!! > 0) {
                _timerCount.value = _timerCount.value!!.minus(1)
                delay(1000)
            }
            isTimerRunning.value = false
        }
    }
    fun setBoolean(data:MutableLiveData<Boolean>, check : Boolean){
        data.value = check
        Log.d("email",emailFocusing.value.toString())
    }

    fun checkEmail(): Boolean {
        val validation = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{5,20}$"
        return Pattern.matches(validation, email.value)
    }
}