package com.softsquared.template.Garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.template.Garamgaebi.model.NetworkingInfoResponse
import com.softsquared.template.Garamgaebi.model.NetworkingParticipantsResponse
import com.softsquared.template.Garamgaebi.model.NetworkingRepository
import kotlinx.coroutines.launch

class NetworkingViewModel : ViewModel(){
    private val networkingRepository = NetworkingRepository()

    private val _networkingParticipants = MutableLiveData<NetworkingParticipantsResponse>()
    val networkingParticipants : LiveData<NetworkingParticipantsResponse>
    get() = _networkingParticipants

    private val _networkingInfo = MutableLiveData<NetworkingInfoResponse>()
    val networkingInfo : LiveData<NetworkingInfoResponse>
    get() = _networkingInfo


    fun getNetworkingParticipants(networkingIdx : Int) {
        viewModelScope.launch{
            val response = networkingRepository.getNetworkingParticipants(1)
            Log.d("networking", response.body().toString())
            if(!response.isSuccessful){
                _networkingParticipants.postValue(response.body())
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

    fun getNetworkingInfo(memberIdx: Int,programIdx: Int) {
        viewModelScope.launch {
            val response = networkingRepository.getNetworkingInfo(0,0)
            Log.d("networking", response.body().toString())
            if(!response.isSuccessful){
                _networkingInfo.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
        }
    }
}