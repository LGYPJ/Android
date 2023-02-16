package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ApplyRepository
import com.garamgaebi.garamgaebi.repository.GatheringRepository
import com.garamgaebi.garamgaebi.repository.NetworkingRepository
import com.garamgaebi.garamgaebi.repository.SeminarRepository
import com.garamgaebi.garamgaebi.model.SeminarDetailInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplyViewModel : ViewModel() {
    private val applyRepository = ApplyRepository()
    private val networkingRepository = NetworkingRepository()
    private val seminarRepository = SeminarRepository()
    private val gatheringRepository = GatheringRepository()

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

    private val _programReady = MutableLiveData<List<GatheringProgramResult>>()
    val programReady : LiveData<List<GatheringProgramResult>>
        get() = _programReady

    //취소정보조회
    private val _cancelInfo = MutableLiveData<CancelInfoResponse>()
    val cancelInfo : LiveData<CancelInfoResponse>
    get() = _cancelInfo


    private val pay : MutableLiveData<String> = MutableLiveData("무료")


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

            }
            else{
                Log.d("error", response.message())
            }
        }

    }

    fun getSeminar(){
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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

    //신청정보조회
    fun getCancel(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = applyRepository.getCancel(GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0),GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0))
            if(response.isSuccessful){
                _cancelInfo.postValue(response.body())
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

    fun convertFee(money : String):String{
        return "${money}원"
    }

    fun feeFree(money : String): String {
        return pay.value.toString()
    }


}