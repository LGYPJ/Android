package com.softsquared.template.Garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.template.Garamgaebi.model.*
import kotlinx.coroutines.launch

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
            Log.d("getHomeSeminar", response.body().toString())

            if (response.isSuccessful) {
                _seminar.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getHomeNetworking() {
        viewModelScope.launch {
            val response = homeRepository.getHomeNetworking()
            Log.d("getHomeNetworking", response.body().toString())

            if(response.isSuccessful) {
                _networking.postValue(response.body())
            } else {
                Log.d("error", response.message())
            }
        }
    }
    fun getHomeUser() {
        viewModelScope.launch {
            val response = homeRepository.getHomeUser()
            Log.d("getHomeUser", response.body().toString())

            if(response.isSuccessful) {
                _user.postValue(response.body())
            } else {
                Log.d("error", response.message())
            }
        }
    }
    
    fun getHomeProgram(memberIdx : Int) {
        viewModelScope.launch {
            val response = homeRepository.getHomeProgram(memberIdx)
            Log.d("getHomeProgram", response.body().toString())

            if(response.isSuccessful) {
                _program.postValue(response.body())
            } else {
                Log.d("error", response.message())
            }
        }
    }
}