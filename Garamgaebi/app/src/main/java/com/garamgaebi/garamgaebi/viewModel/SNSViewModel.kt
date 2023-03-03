package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SNSViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()


    var snsIdx: Int = -1

    val snsType = MutableLiveData<String>()
    init { snsType.value = ""}

    val snsTypeIsValid = MutableLiveData<Boolean>()
    init { snsTypeIsValid.value = false}



    val snsAddress = MutableLiveData<String>()
    init { snsAddress.value = ""}

    val snsAddressIsValid = MutableLiveData<Boolean>()
    init { snsAddressIsValid.value = false}


    //유효성 검사를 위한 부분 1)SNS 종류 2)SNS 주소

    //포커싱 감지
    val snsTypeFocusing = MutableLiveData<Boolean>(false)
    val snsAddressFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var addressFirst = MutableLiveData<Boolean>(true)
    var typeFirst = MutableLiveData<Boolean>(true)

    //sns종류에 따른 address hint 문구
    var typeInputDesc = MutableLiveData<String>("")
    var addressInputDesc = MutableLiveData<String>("")

    var typeState = MutableLiveData<String>("")
    var linkState = MutableLiveData<String>("")


    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        Log.d("링크 focusing입니다",snsAddressFocusing.value.toString())
        Log.d("링크 유효성입니다",snsAddressIsValid.value.toString())
    }


    val _add = MutableLiveData<AddSNSDataResponse>()
    val add : LiveData<AddSNSDataResponse>
        get() = _add
    //SNS 추가
    fun postSNSInfo() {
        var toAddress : String = snsAddress.value.toString()
        if(snsType.value.equals("인스타그램")){
            toAddress = "@$toAddress"
        }
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getCheckAddSNS(AddSNSData(myMemberIdx,toAddress,snsType.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _add.value = response.body()
                }
                Log.d("sns_add_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    val _patch = MutableLiveData<BooleanResponse>()
    val patch : LiveData<BooleanResponse>
        get() = _patch

    //SNS 수정
    fun patchSNSInfo() {
        var toAddress : String = snsAddress.value.toString()
        if(snsType.value.equals("인스타그램")){
            toAddress = "@$toAddress"

        }
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.patchSNS(SNSData(snsIdx,toAddress,snsType.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _patch.value = response.body()
                }
                Log.d("sns_patch_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _delete = MutableLiveData<BooleanResponse>()
    val delete : LiveData<BooleanResponse>
        get() = _delete
    //SNS 삭제
    fun deleteSNSInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.deleteSNS(snsIdx)
            //Log.d("sns_add", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _delete.value = response.body()
                }
                Log.d("sns_delete_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

}
