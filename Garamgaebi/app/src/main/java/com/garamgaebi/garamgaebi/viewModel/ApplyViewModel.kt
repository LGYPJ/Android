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
import kotlinx.coroutines.runBlocking

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
    private var free : String = "무료"


    //신청하기 request
    //이름
    val inputName : MutableLiveData<String> = MutableLiveData("")
    //닉네임
    val inputNickName : MutableLiveData<String> = MutableLiveData("")
    //전화번호
    val inputPhone : MutableLiveData<String> = MutableLiveData("")
    //계좌번호
    val inputAccount : MutableLiveData<String> = MutableLiveData("")

    //유효성 검사
    // 포커싱 감저
    val nameFocusing = MutableLiveData<Boolean>(false)
    val nicknameFocusing = MutableLiveData<Boolean>(false)
    val phoneFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var nameFirst = MutableLiveData<Boolean>(true)
    var nicknameFirst = MutableLiveData<Boolean>(true)
    var phoneFirst = MutableLiveData<Boolean>(true)

    //유효성 문구
    var nameState = MutableLiveData<String>("")
    var nicknameState = MutableLiveData<String>("")
    var phoneState = MutableLiveData<String>("")

    val nameIsValid = MutableLiveData<Boolean>()
    init {
        nameIsValid.value = false
    }

    val nicknameIsValid = MutableLiveData<Boolean>()
    init {
        nicknameIsValid.value = false
    }

    val phoneIsValid = MutableLiveData<Boolean>()
    init {
        phoneIsValid.value = false
    }

    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
    }

  fun postCancel(cancelRequest: CancelRequest) {
      Log.d("canceldd", "ddddd".toString())
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
            var memberIdx = 0
            var programIdx = 0

            val IdxCheck = runBlocking{ // 비동기 작업 시작
                memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
                programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            }
            val response = applyRepository.postEnroll(
                EnrollRequest(
                    memberIdx,
                    programIdx,
                    inputName.value.toString(),
                    inputNickName.value.toString(),
                    inputPhone.value.toString()
                )
            )
            if(response.isSuccessful) {
                _enroll.postValue(response.body())
                _enrollReq.value = EnrollRequest(
                    memberIdx,
                    programIdx,
                    inputName.value.toString(),
                    inputNickName.value.toString(),
                    inputPhone.value.toString()
                )
                    val putData = runBlocking{ // 비동기 작업 시작
                        GaramgaebiApplication().saveStringToDataStore("inputName",inputName.value.toString())
                        GaramgaebiApplication().saveStringToDataStore("inputNickName",inputNickName.value.toString())
                        GaramgaebiApplication().saveStringToDataStore("inputPhone",inputPhone.value.toString())
                        GaramgaebiApplication().saveIntToDataStore ("enrollIdx",programIdx)
                    }
            }
            else{
                Log.d("error", response.message())
            }
        }

    }

    fun getSeminar(){
        var memberIdx = 0
        var programIdx = 0

        val IdxCheck = runBlocking{ // 비동기 작업 시작
            memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
        }
        viewModelScope.launch(Dispatchers.IO) {
            var programIdx = 0

            val IdxCheck = runBlocking{ // 비동기 작업 시작
                programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            }
            val response = seminarRepository.getSeminarDetail(programIdx, memberIdx)
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
        var memberIdx = 0
        var programIdx = 0

        val IdxCheck = runBlocking{ // 비동기 작업 시작
            memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
        }
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkingRepository.getNetworkingInfo(programIdx,memberIdx)
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
        var memberIdx = 0
        var programIdx = 0

        val IdxCheck = runBlocking{ // 비동기 작업 시작
            memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
        }
        viewModelScope.launch(Dispatchers.IO) {
            val response = applyRepository.getCancel(memberIdx,programIdx)
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
        Log.d("applyfree", money)
        return "${money}원"
    }

    fun feeFree(money : String): String {
        Log.d("applyfree1", money)
        return pay.value.toString()
    }


}