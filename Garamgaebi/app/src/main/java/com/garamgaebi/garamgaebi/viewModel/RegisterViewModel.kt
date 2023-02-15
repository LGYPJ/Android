package com.garamgaebi.garamgaebi.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.model.RegisterEmailResponse
import com.garamgaebi.garamgaebi.repository.RegisterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val socialEmail = MutableLiveData<String>("")
    val uniEmail = MutableLiveData<String>("")
    val emailFocusing = MutableLiveData<Boolean>(false)

    val timerFirst = MutableLiveData<Boolean>(true)
    val isTimerRunning = MutableLiveData<Boolean>(false)

    val authNum = MutableLiveData<String>("")
    val authNumFocusing = MutableLiveData<Boolean>(false)

    val isEmailValid = MutableLiveData<Boolean>(false)
    val isNumValid = MutableLiveData<Boolean>(false)
    val isAuthWrong = MutableLiveData<Boolean>(false)
    val isCompleteAuth = MutableLiveData<Boolean>(false)

    private val _emailConfirm = MutableLiveData<RegisterEmailResponse>()
    val emailConfirm : LiveData<RegisterEmailResponse>
        get() = _emailConfirm

    var _timerCount = MutableLiveData<Int>(5)
    lateinit var timer : Job



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
        return Pattern.matches(validation, uniEmail.value)
    }
    fun checkAuthNum() : Boolean {
        val validation = "^[0-9]{6}$"
        return Pattern.matches(validation, authNum.value)
    }
    fun getEmail(context: Context) : String {
        return uniEmail.value+context.getString(R.string.register_email_gachon)
    }
}