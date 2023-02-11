package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.HomeRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

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

            if (response.isSuccessful && response.body()?.result != null) {
                _seminar.postValue(response.body())
                Log.d("getHomeSeminar", "${response.body()}")
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

            if (response.isSuccessful && response.body()?.result != null) {
                _networking.postValue(response.body())
                Log.d("getHomeNetworking", "${response.body()}")
            } else {
                Log.d("error", "getHomeNetworking : "+response.message())
            }
        }
    }
    fun getHomeUser() {
        viewModelScope.launch {
            val response = homeRepository.getHomeUser()
            Log.d("getHomeUser", "$response")

            if (response.isSuccessful && response.body()?.result != null) {
                (response.body()!!.result as ArrayList).forEach { item ->
                    if(item.memberIdx == 1) (response.body()!!.result as ArrayList).remove(item)
                }
                if ((response.body()!!.result as ArrayList).size == 11) {
                    (response.body()!!.result as ArrayList).removeAt(10)
                }
                
                _user.postValue(response.body())
                Log.d("getHomeUser", "${response.body()}")
            } else {
                Log.d("error", "getHomeUser : "+response.message())
            }
        }
    }
    
    fun getHomeProgram(memberIdx : Int) {
        viewModelScope.launch {
            val response = homeRepository.getHomeProgram(memberIdx)
            Log.d("getHomeProgram", "$response")

            if (response.isSuccessful && response.body()?.result != null) {
                _program.postValue(response.body())
                Log.d("getHomeProgram", "${response.body()}")
            } else {
                Log.d("error", "getHomeProgram : "+response.message())
            }
        }
    }

    fun getDay(date : String) : String{
        val dataFormat = SimpleDateFormat("yyyy-MM-dd")
        return dataFormat.format(date).toString()

    }
}