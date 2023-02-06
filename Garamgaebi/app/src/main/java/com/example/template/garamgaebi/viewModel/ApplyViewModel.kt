package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.databinding.InverseMethod
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import kotlinx.coroutines.launch

class ApplyViewModel : ViewModel() {
    private val applyRepository = ApplyRepository()

    private val _cancel = MutableLiveData<CancelResponse>()
    val cancel : LiveData<CancelResponse>
    get() = _cancel

    private val _enroll = MutableLiveData<EnrollResponse>()
    val enroll : LiveData<EnrollResponse>
    get() = _enroll

    //신청하기 request
    //이름
    val inputName : MutableLiveData<String> = MutableLiveData("")
    //닉네임
    val inputNickName : MutableLiveData<String> = MutableLiveData("")
    //전화번호
    val inputPhone : MutableLiveData<String> = MutableLiveData("")

    fun postCancel(cancelRequest: CancelRequest) {
        viewModelScope.launch {
            val response = applyRepository.postCancel(cancelRequest)
            Log.d("cancel", response.body().toString())
            if(response.body()?.isSuccess!!){
                _cancel.postValue(response.body())
            }
            else {
                response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    fun postEnroll(){
        viewModelScope.launch {
            val response = applyRepository.postEnroll(EnrollRequest(1,6, inputName.value.toString(), inputNickName.value.toString(), inputPhone.value.toString()))
            Log.d("enroll", EnrollRequest(1,6, inputName.value.toString(), inputNickName.value.toString(), inputPhone.value.toString()).toString())
            if(response.isSuccessful){
                _enroll.postValue(response.body())
            }
            else{
                response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    fun getNameText() : MutableLiveData<String> = inputName
    fun getNickNameText() : MutableLiveData<String> = inputNickName
    fun getPhoneText() : MutableLiveData<String> = inputPhone

}