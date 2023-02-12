package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.model.NetworkingInfoResponse
import com.example.template.garamgaebi.model.NetworkingParticipantsResponse
import com.example.template.garamgaebi.model.NetworkingResult
import com.example.template.garamgaebi.repository.NetworkingRepository
import kotlinx.coroutines.launch

class NetworkingViewModel : ViewModel(){
    private val networkingRepository = NetworkingRepository()

    private val _networkingParticipants = MutableLiveData<List<NetworkingResult>>()
    val networkingParticipants : LiveData<List<NetworkingResult>>
    get() = _networkingParticipants

    private val _networkingInfo = MutableLiveData<NetworkingInfoResponse>()
    val networkingInfo : LiveData<NetworkingInfoResponse>
    get() = _networkingInfo


    fun getNetworkingParticipants() {
        viewModelScope.launch{
            val response = networkingRepository.getNetworkingParticipants(GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0),
                GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))
            Log.d("networking", response.body().toString())
            if(response.isSuccessful){
                _networkingParticipants.postValue(response.body()?.result?.participantList)
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
}