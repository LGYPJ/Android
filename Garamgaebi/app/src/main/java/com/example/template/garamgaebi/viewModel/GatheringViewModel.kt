package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
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
            if (response.isSuccessful) {
                _seminarThisMonth.postValue(response.body())
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

            if (response.isSuccessful) {
                _seminarNextMonth.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getGatheringSeminarClosed() {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringSeminarClosed()
            Log.d("getGatheringSeminarClosed", response.toString())
            if (response.isSuccessful) {
                _seminarClosed.postValue(response.body())
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
            if (response.isSuccessful) {
                _networkingThisMonth.postValue(response.body())
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
            if (response.isSuccessful) {
                _networkingNextMonth.postValue(response.body())
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
            if (response.isSuccessful) {
                _networkingClosed.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


    fun getGatheringProgramReady(memberIdx : Int) {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringProgramReady(memberIdx)
            Log.d("getGatheringProgramReady", "$response")

            if (response.isSuccessful) {
                _programReady.postValue(response.body())
            }
        }
    }
    fun getGatheringProgramClosed(memberIdx : Int) {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringProgramClosed(memberIdx)
            Log.d("getGatheringProgramClosed", "$response")

            if (response.isSuccessful) {
                _programClosed.postValue(response.body())
            }
        }
    }

    fun getDay(date : String) : String{
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dataFormat = SimpleDateFormat("yyyy-MM-dd")
        return dataFormat.format(formatter.parse(date))

    }
}
