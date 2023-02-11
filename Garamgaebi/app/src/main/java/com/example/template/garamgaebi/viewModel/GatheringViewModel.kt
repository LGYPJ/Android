package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.GatheringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

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
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringSeminarThisMonth()
            Log.d("getGatheringSeminarThisMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _seminarThisMonth.postValue(response.body())
                Log.d("getGatheringSeminarThisMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringSeminarNextMonth() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringSeminarNextMonth()
            Log.d("getGatheringSeminarNextMonth", "$response")

            if (response.isSuccessful && response.body() != null) {
                _seminarNextMonth.postValue(response.body())
                Log.d("getGatheringSeminarNextMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringSeminarClosed() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringSeminarClosed()
            Log.d("getGatheringSeminarClosed", "$response")
            if (response.isSuccessful && response.body() != null) {
                _seminarClosed.postValue(response.body())
                Log.d("getGatheringSeminarClosed", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


    fun getGatheringNetworkingThisMonth() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringNetworkingThisMonth()
            Log.d("getGatheringNetworkingThisMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingThisMonth.postValue(response.body())
                Log.d("getGatheringNetworkingThisMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringNetworkingNextMonth() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringNetworkingNextMonth()
            Log.d("getGatheringNetworkingNextMonth", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingNextMonth.postValue(response.body())
                Log.d("getGatheringNetworkingNextMonth", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringNetworkingClosed() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringNetworkingClosed()
            Log.d("getGatheringNetworkingClosed", "$response")
            if (response.isSuccessful && response.body() != null) {
                _networkingClosed.postValue(response.body())
                Log.d("getGatheringNetworkingClosed", "${response.body()}")
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


    fun getGatheringProgramReady(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response = gatheringRepository.getGatheringProgramReady(22)
            Log.d("getGatheringProgramReady", "$response")

            if (response.isSuccessful && response.body() != null) {
                //_programReady.postValue(response.body())
                _programReady.postValue(response.body())
                Log.d("getGatheringProgramReady", "${response.body()}")
            }
        }
    }
    fun getGatheringProgramClosed(memberIdx : Int) {
        viewModelScope.launch (Dispatchers.IO){
            val response = gatheringRepository.getGatheringProgramClosed(memberIdx)
            Log.d("getGatheringProgramClosed", "$response")

            if (response.isSuccessful && response.body() != null) {
                _programClosed.postValue(response.body())
                Log.d("getGatheringProgramClosed", "${response.body()}")
            }
        }
    }

}
