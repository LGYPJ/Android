package com.example.template.garamgaebi.viewModel

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.launch

class SNSViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
    var addressFirst : Boolean = false
    var typeFirst: Boolean = false

    val snsType = MutableLiveData<String>()
    init { snsType.value = ""}

    val snsTypeIsValid = MutableLiveData<Boolean>()
    init { snsTypeIsValid.value = false}

    val snsAddress = MutableLiveData<String>()
    init { snsAddress.value = ""}

    val snsAddressIsValid = MutableLiveData<Boolean>()
    init { snsAddressIsValid.value = false}


    fun logTest(view:EditText, check : Boolean,isValid : Boolean){
        Log.d("focus_check_log",view.toString() + check.toString() + isValid.toString())
    }
    fun setTrueAddress(){
        addressFirst = true
    }
    fun setTrueType(){
        typeFirst = true
    }

    private val _add = MutableLiveData<AddSNSDataResponse>()
    val add : LiveData<AddSNSDataResponse>
        get() = _add
    //SNS 추가
    fun postSNSInfo() {
        viewModelScope.launch {
            val response = profileRepository.getCheckAddSNS(SNSData(1,snsAddress.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _add.postValue(response.body())
                Log.d("sns_add_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
}
