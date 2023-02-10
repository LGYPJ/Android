package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.ApplyRepository
import com.example.template.garamgaebi.repository.NetworkingRepository
import com.example.template.garamgaebi.repository.SeminarRepository
import com.example.template.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet

class ApplyViewModel : ViewModel() {
    private val applyRepository = ApplyRepository()
    private val networkingRepository = NetworkingRepository()
    private val seminarRepository = SeminarRepository()

    private val _cancel = MutableLiveData<CancelResponse>()
    val cancel : LiveData<CancelResponse>
    get() = _cancel

    private val _enroll = MutableLiveData<EnrollResponse>()
    val enroll : LiveData<EnrollResponse>
    get() = _enroll

    private val _networkingInfo = MutableLiveData<NetworkingInfoResponse>()
    val networkingInfo : LiveData<NetworkingInfoResponse>
        get() = _networkingInfo

    private val _seminarInfo = MutableLiveData<SeminarDetailInfoResponse>()
    val seminarInfo : LiveData<SeminarDetailInfoResponse>
        get() = _seminarInfo

    private val _enrollReq = MutableLiveData<EnrollRequest>()
    val enrollReq : LiveData<EnrollRequest>
    get() = _enrollReq


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
            val response = applyRepository.postEnroll(
                EnrollRequest(
                    GaramgaebiApplication.sSharedPreferences.getInt(
                        "memberIdx",
                        0
                    ),
                    GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0),
                    inputName.value.toString(),
                    inputNickName.value.toString(),
                    inputPhone.value.toString()
                )
            )
            if(response.isSuccessful) {
                _enroll.postValue(response.body())
                _enrollReq.value = EnrollRequest(
                    GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0),
                    GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0),
                    inputName.value.toString(),
                    inputNickName.value.toString(),
                    inputPhone.value.toString()
                )

                with(GaramgaebiApplication.sSharedPreferences.edit()) {
                    putInt(
                        "enrollIdx",
                        GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0)
                    )
                    putString("inputName", inputName.value.toString())
                    putString("inputNickName", inputNickName.value.toString())
                    putString("inputPhone", inputPhone.value.toString())
                    apply()
                }
                /*val set = java.util.HashSet<String>()
                    set.add(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0).toString())
                    set.add(inputName.value.toString())
                    set.add(inputNickName.value.toString())
                    set.add(inputPhone.value.toString())
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putStringSet("enroll", set)
                        .apply()*/
            }
            else{
                Log.d("error", response.message())
            }
        }

    }

    fun getSeminar(){
        viewModelScope.launch {
            val response = seminarRepository.getSeminarDetail(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0), GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))
            Log.d("seminarDetail", response.body().toString())
            if(response.isSuccessful) {
                //날짜 데이터 변환
                response.body()?.result?.date =
                    response.body()?.result?.date?.let { GaramgaebiFunction().getDate(it) }.toString()
                response.body()?.result?.endDate =
                    response.body()?.result?.endDate?.let { GaramgaebiFunction().getDate(it) }.toString()

                _seminarInfo.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

    fun getNetworking(){
        viewModelScope.launch {
            val response = networkingRepository.getNetworkingInfo(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0), GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))
            Log.d("networking", response.body().toString())
            if(response.isSuccessful){
                //날짜 데이터 변환
                response.body()?.result?.date =
                    response.body()?.result?.date?.let { GaramgaebiFunction().getDate(it) }.toString()
                response.body()?.result?.endDate =
                    response.body()?.result?.endDate?.let { GaramgaebiFunction().getDate(it) }.toString()
                _networkingInfo.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
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