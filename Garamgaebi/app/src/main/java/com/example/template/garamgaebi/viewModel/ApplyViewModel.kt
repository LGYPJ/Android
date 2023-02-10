package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.ApplyRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
    //계좌번호
    val inputAccount : MutableLiveData<String> = MutableLiveData("")

    fun postCancel(cancelRequest: CancelRequest) {
        viewModelScope.launch {
            val response = applyRepository.postCancel(cancelRequest)
            Log.d("cancel", response.body().toString())
            if(response.isSuccessful){
                _cancel.postValue(response.body())
            }
            else {
                response.body()?.errorMessage?.let { Log.d("error", it) }
            }
        }
    }

    fun postEnroll(){
        viewModelScope.launch {
            val response = applyRepository.postEnroll(EnrollRequest(GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0),8, inputName.value.toString(), inputNickName.value.toString(), inputPhone.value.toString()))
            Log.d("enroll", EnrollRequest(GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0),8, inputName.value.toString(), inputNickName.value.toString(), inputPhone.value.toString()).toString())
            if(response.isSuccessful){
                _enroll.postValue(response.body())
            }
            else{
                response.body()?.errorMessage?.let { Log.d("error", it) }
            }
        }
    }

    fun getNameText() : MutableLiveData<String> = inputName
    fun getNickNameText() : MutableLiveData<String> = inputNickName
    fun getPhoneText() : MutableLiveData<String> = inputPhone
    fun getAccountText() : MutableLiveData<String> = inputAccount

    /*fun convertDate(date: String?): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm '시'")
        return date?.let { formatter.parse(it)?.toString() }
    }*/

}