package com.garamgaebi.garamgaebi.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.model.PresentationResult
import com.garamgaebi.garamgaebi.model.SeminarDetailInfoResponse
import com.garamgaebi.garamgaebi.model.SeminarPresentResponse
import com.garamgaebi.garamgaebi.model.SeminarResult
import com.garamgaebi.garamgaebi.repository.SeminarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SeminarViewModel : ViewModel(){
    private val seminarRepository = SeminarRepository()

    private val _presentation = MutableLiveData<SeminarPresentResponse>()
    val presentation : LiveData<SeminarPresentResponse>
    get() = _presentation


    private val _seminarParticipants = MutableLiveData<List<SeminarResult>>()
    val seminarParticipants : LiveData<List<SeminarResult>>
    get() = _seminarParticipants

    private val _info = MutableLiveData<SeminarDetailInfoResponse>()
    val info : LiveData<SeminarDetailInfoResponse>
    get() = _info

    //val pay : MutableLiveData<String> = MutableLiveData("무료")
    fun getSeminarsInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            //var response = seminarRepository.getSeminarsInfo(sSharedPreferences.getInt("programIdx", 0))
            var programIdx = 0
            val IdxCheck = runBlocking{ // 비동기 작업 시작
                programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!

            }
            var response = seminarRepository.getSeminarsInfo(programIdx)

            Log.d("seminarPresent", response.body().toString())
            if (response.isSuccessful) {
                viewModelScope.launch(Dispatchers.Main){
                    _presentation.value = response.body()
                }
                //_presentation.postValue(response.body())
                //_present.postValue(response.body()?.result as ArrayList<PresentationResult>?)
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getSeminarParticipants() {
        var programIdx = 0
        var memberIdx = 0
        val IdxCheck = runBlocking{ // 비동기 작업 시작
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
        }
        viewModelScope.launch (Dispatchers.IO){
            val response = seminarRepository.getSeminarParticipants(programIdx,memberIdx)
            Log.d("seminarParticipants", response.body().toString())
            if (response.isSuccessful) {
                viewModelScope.launch(Dispatchers.Main){
                    _seminarParticipants.value = response.body()?.result?.participantList
                }
                //_seminarParticipants.postValue(response.body()?.result?.participantList)
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    fun getSeminarDetail() {
        var programIdx = 0
        var memberIdx = 0
        val IdxCheck = runBlocking{ // 비동기 작업 시작
            programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
        }
        viewModelScope.launch (Dispatchers.IO){
            val response = seminarRepository.getSeminarDetail(programIdx,memberIdx)
            Log.d("seminarDetail", response.body().toString())
            if(response.isSuccessful) {
                viewModelScope.launch(Dispatchers.Main){
                    //날짜 데이터 변환
                    response.body()?.result?.date =
                        response.body()?.result?.date?.let { GaramgaebiFunction().getDate(it) }.toString()
                    response.body()?.result?.endDate =
                        response.body()?.result?.endDate?.let { GaramgaebiFunction().getDate(it) }.toString()
                    _info.value = response.body()
                }
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

}