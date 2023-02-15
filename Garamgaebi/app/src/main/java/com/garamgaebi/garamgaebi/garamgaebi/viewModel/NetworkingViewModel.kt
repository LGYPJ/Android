package com.garamgaebi.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoResponse
import com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingParticipantsResult
import com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingResult
import com.garamgaebi.garamgaebi.garamgaebi.repository.NetworkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            val response = networkingRepository.getNetworkingParticipants(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0),
                GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))
            Log.d("networking", response.body().toString())
            if(response.isSuccessful){
                _networkingParticipants.postValue(response.body()?.result?.participantList)
                _networkingActive.postValue(response.body()?.result)
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
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkingRepository.getNetworkingInfo(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0), GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))

            GaramgaebiApplication.sSharedPreferences
                .edit().putString("startDate",
                    response.body()?.result?.let { GaramgaebiFunction().getDateYMD(it.date) })
                .apply()

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
}