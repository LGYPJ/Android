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

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val email = MutableLiveData<String>()
    val authNum = MutableLiveData<String>()
    val isEmailValid = MutableLiveData<Boolean>()
    val isNumValid = MutableLiveData<Boolean>()
    val isCompleteAuth = MutableLiveData<Boolean>()


    private val _emailConfirm = MutableLiveData<RegisterEmailResponse>()
    val emailConfirm : LiveData<RegisterEmailResponse>
        get() = _emailConfirm

    val _timerCount = MutableLiveData<Int>()
    private lateinit var timer : Job
    val timerCount : LiveData<Int>
        get() = _timerCount

    init {
        email.value = ""
        authNum.value = ""
        isEmailValid.value = false
        isNumValid.value = false
        isCompleteAuth.value = false
        _timerCount.value = 180
    }

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
        _timerCount.value = 180
        timer = viewModelScope.launch {
            while(_timerCount.value!! > 0) {
                _timerCount.value = _timerCount.value!!.minus(1)
                delay(1000L)
            }
        }
    }
    fun emailBtnText(context: Context) : String{
        if(!::timer.isInitialized) return toString(R.string.send_email, context)
        return toString(R.string.re_send_email, context)
    }

    fun toString(resId : Int, context : Context) : String{
        return context.resources.getString(resId)
    }
}