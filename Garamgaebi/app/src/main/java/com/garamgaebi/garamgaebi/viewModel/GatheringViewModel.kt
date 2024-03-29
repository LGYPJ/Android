package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.GatheringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GatheringViewModel: ViewModel() {
    private val gatheringRepository = GatheringRepository()
    // 세미나
    private val _seminarThisMonth = MutableLiveData<GatheringSeminarResponse>()
    val seminarThisMonth : LiveData<GatheringSeminarResponse>
    get() = _seminarThisMonth

    private val _seminarNextMonth = MutableLiveData<GatheringSeminarResponse>()
    val seminarNextMonth : LiveData<GatheringSeminarResponse>
    get() = _seminarNextMonth

    private val _seminarClosed = MutableLiveData<GatheringSeminarClosedResponse>()
    val seminarClosed : LiveData<GatheringSeminarClosedResponse>
    get() = _seminarClosed

    // 네트워킹
    private val _networkingThisMonth = MutableLiveData<GatheringNetworkingResponse>()
    val networkingThisMonth : LiveData<GatheringNetworkingResponse>
    get() = _networkingThisMonth

    private val _networkingNextMonth = MutableLiveData<GatheringNetworkingResponse>()
    val networkingNextMonth : LiveData<GatheringNetworkingResponse>
    get() = _networkingNextMonth

    private val _networkingClosed = MutableLiveData<GatheringNetworkingClosedResponse>()
    val networkingClosed : LiveData<GatheringNetworkingClosedResponse>
    get() = _networkingClosed

    // 내 모임
    private val _programReady = MutableLiveData<GatheringProgramResponse>()
    val programReady : LiveData<GatheringProgramResponse>
    get() = _programReady

    private val _programClosed = MutableLiveData<GatheringProgramResponse>()
    val programClosed : LiveData<GatheringProgramResponse>
    get() = _programClosed

    fun getGatheringSeminarThisMonth() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringSeminarThisMonth()
            Log.d("getGatheringSeminarThisMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _seminarThisMonth.value = response.body()
                Log.d("getGatheringSeminarThisMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringSeminarNextMonth() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringSeminarNextMonth()
            Log.d("getGatheringSeminarNextMonth", "$response")

            if (response.isSuccessful && response.body() != null) {
                _seminarNextMonth.value = response.body()
                Log.d("getGatheringSeminarNextMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringSeminarClosed() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringSeminarClosed()
            Log.d("getGatheringSeminarClosed", "$response")
            if (response.isSuccessful && response.body() != null) {
                _seminarClosed.value = response.body()
                Log.d("getGatheringSeminarClosed", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


    fun getGatheringNetworkingThisMonth() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringNetworkingThisMonth()
            Log.d("getGatheringNetworkingThisMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingThisMonth.value = response.body()
                Log.d("getGatheringNetworkingThisMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringNetworkingNextMonth() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringNetworkingNextMonth()
            Log.d("getGatheringNetworkingNextMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingNextMonth.value = response.body()
                Log.d("getGatheringNetworkingNextMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringNetworkingClosed() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = gatheringRepository.getGatheringNetworkingClosed()
            Log.d("getGatheringNetworkingClosed", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingClosed.value = response.body()
                Log.d("getGatheringNetworkingClosed", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


    fun getGatheringProgramReady(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response = gatheringRepository.getGatheringProgramReady(memberIdx)
            Log.d("getGatheringProgramReady", "$response")

            if (response.isSuccessful && response.body() != null) {
                //_programReady.postValue(response.body())
                _programReady.postValue(response.body())
                Log.d("getGatheringProgramReady", "${response.body()}")
            }
        }
    }
    fun getGatheringProgramClosed(memberIdx : Int) {
        viewModelScope.launch (Dispatchers.Main){
            val response = gatheringRepository.getGatheringProgramClosed(memberIdx)
            Log.d("getGatheringProgramClosed", "$response")

            if (response.isSuccessful && response.body() != null) {
                _programClosed.value = response.body()
                Log.d("getGatheringProgramClosed", "${response.body()}")
            }
        }
    }

}
