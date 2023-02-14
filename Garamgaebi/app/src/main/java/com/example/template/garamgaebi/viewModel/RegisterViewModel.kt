package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.RegisterEmailResponse
import com.example.template.garamgaebi.repository.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val email = MutableLiveData<String>()
    val authNum = MutableLiveData<String>()
    val isEmailValid = MutableLiveData<Boolean>()
    val isNumValid = MutableLiveData<Boolean>()
    val isCompleteAuth = MutableLiveData<Boolean>()

    init {
        email.value = ""
        authNum.value = ""
        isEmailValid.value = false
        isNumValid.value = false
        isCompleteAuth.value = false
    }

    private val _emailConfirm = MutableLiveData<RegisterEmailResponse>()
    val emailConfirm : LiveData<RegisterEmailResponse>
        get() = _emailConfirm
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
}