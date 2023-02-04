package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel : ViewModel(){
    private val homeRepository = HomeRepository()

    private val _seminar = MutableLiveData<HomeSeminarResponse>()
    val seminar : LiveData<HomeSeminarResponse>
        get() = _seminar

    private val _networking = MutableLiveData<HomeNetworkingResponse>()
    val networking : LiveData<HomeNetworkingResponse>
        get() = _networking

    private val _user = MutableLiveData<HomeUserResponse>()
    val user : LiveData<HomeUserResponse>
        get() = _user

    private val _program = MutableLiveData<HomeProgramResponse>()
    val program : LiveData<HomeProgramResponse>
        get() = _program
    
    fun getHomeSeminar() {
        viewModelScope.launch {
            val response = homeRepository.getHomeSeminar()
            Log.d("getHomeSeminar", "$response")

            if (response.isSuccessful) {
                _seminar.postValue(response.body())
            }
            else {
                Log.d("error", "getHomeSeminar : "+response.message())
            }
        }
    }
    fun getHomeNetworking() {
        viewModelScope.launch {
            val response = homeRepository.getHomeNetworking()
            Log.d("getHomeNetworking", "$response")

            if(response.isSuccessful) {
                _networking.postValue(response.body())
            } else {
                Log.d("error", "getHomeNetworking : "+response.message())
            }
        }
    }
    fun getHomeUser() {
        viewModelScope.launch {
            val response = homeRepository.getHomeUser()
            Log.d("getHomeUser", "$response")

            if(response.isSuccessful) {
                _user.postValue(response.body())
            } else {
                Log.d("error", "getHomeUser : "+response.message())
            }
        }
    }
    
    fun getHomeProgram(memberIdx : Int) {
        viewModelScope.launch {
            val response = homeRepository.getHomeProgram(memberIdx)
            Log.d("getHomeProgram", "$response")

            if(response.isSuccessful) {
                _program.postValue(response.body())
            } else {
                Log.d("error", "getHomeProgram : "+response.message())
            }
        }
    }

    fun getDay(date : String) {
        val temp = LocalDateTime.now()

    }
}