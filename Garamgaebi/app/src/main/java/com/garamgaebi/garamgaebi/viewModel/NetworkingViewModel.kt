package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.model.NetworkingInfoResponse
import com.garamgaebi.garamgaebi.model.NetworkingParticipantsResult
import com.garamgaebi.garamgaebi.model.NetworkingResult
import com.garamgaebi.garamgaebi.repository.NetworkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NetworkingViewModel : ViewModel(){
    private val networkingRepository = NetworkingRepository()

    //어댑터를 위한 데이터
    private val _networkingParticipants = MutableLiveData<List<NetworkingResult>>()
    val networkingParticipants : LiveData<List<NetworkingResult>>
    get() = _networkingParticipants

    // 아이스브레이킹 버튼 활성화를 위한 데이터
    private val _networkingActive = MutableLiveData<NetworkingParticipantsResult>()
    val networkingActive : LiveData<NetworkingParticipantsResult>
    get() = _networkingActive

    private val _networkingInfo = MutableLiveData<NetworkingInfoResponse>()
    val networkingInfo : LiveData<NetworkingInfoResponse>
    get() = _networkingInfo

    fun getNetworkingParticipants() {
        viewModelScope.launch(Dispatchers.IO){
            var memberIdx = 0
            var programIdx = 0

            val IdxCheck = runBlocking{ // 비동기 작업 시작
                memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
                programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            }
            val response = networkingRepository.getNetworkingParticipants(programIdx,
                memberIdx)
            Log.d("networking", response.body().toString())
            if(response.isSuccessful){
                /*_networkingParticipants.postValue(response.body()?.result?.participantList)
                _networkingActive.postValue(response.body()?.result)*/
                viewModelScope.launch(Dispatchers.Main){
                    Log.d("대참사", response.toString())
                    _networkingParticipants.value = response.body()?.result?.participantList
                    _networkingActive.value = response.body()?.result
                }
            }
            else {
                Log.d("error", response.message())
            }
            /*if(response.isSuccessful){
                _networkingParticipants.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }*/
        }
    }

    fun getNetworkingInfo() {
        viewModelScope.launch(Dispatchers.Main) {
            var memberIdx = 0
            var programIdx = 0

            val IdxCheck = runBlocking{ // 비동기 작업 시작
                memberIdx  = GaramgaebiApplication().loadIntData("memberIdx")!!
                programIdx  = GaramgaebiApplication().loadIntData("programIdx")!!
            }

            val response = networkingRepository.getNetworkingInfo(programIdx, memberIdx)

//            GaramgaebiApplication.sSharedPreferences
//                .edit().putString("startDate",
//                    response.body()?.result?.let { GaramgaebiFunction().getDateYMD(it.date) })
//                .apply()

            val dateCheck = runBlocking{ // 비동기 작업 시작
                response.body()?.result?.let {
                    GaramgaebiFunction().getDateYMD(
                        it.date)
                }?.let { GaramgaebiApplication().saveStringToDataStore("startDate", it) }!!
            }

            Log.d("networking", response.body().toString())
            if(response.isSuccessful){
                viewModelScope.launch(Dispatchers.Main) {
                    //날짜 데이터 변환
                    response.body()?.result?.date =
                        response.body()?.result?.date?.let { GaramgaebiFunction().getDate(it) }.toString()
                    response.body()?.result?.endDate =
                        response.body()?.result?.endDate?.let { GaramgaebiFunction().getDate(it) }.toString()
                    //_networkingInfo.postValue(response.body())
                    _networkingInfo.value = response.body()
                }
            }
            else{
                Log.d("error", response.message())
            }
        }
    }
}