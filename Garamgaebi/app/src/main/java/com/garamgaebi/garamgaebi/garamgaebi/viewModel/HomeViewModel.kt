package com.garamgaebi.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.garamgaebi.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
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

    private val _notification = MutableLiveData<NotificationResponse>()
    val notification : LiveData<NotificationResponse>
        get() = _notification

    private val _notificationScroll = MutableLiveData<NotificationResponse>()
    val notificationScroll : LiveData<NotificationResponse>
        get() = _notificationScroll

    private val _notificationUnread = MutableLiveData<NotificationUnreadResponse>()
    val notificationUnread : LiveData<NotificationUnreadResponse>
        get() = _notificationUnread

    init{
        getNotificationUnread(GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0))
    }

    fun getHomeSeminar() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getHomeSeminar()
            Log.d("getHomeSeminar", "$response")

            if (response.isSuccessful && response.body() != null) {
                _seminar.postValue(response.body())
                Log.d("getHomeSeminar", "${response.body()}")
            }
            else {
                Log.d("error", "getHomeSeminar : "+response.message())
            }
        }
    }
    fun getHomeNetworking() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getHomeNetworking()
            Log.d("getHomeNetworking", "$response")

            if (response.isSuccessful && response.body() != null) {
                _networking.postValue(response.body())
                Log.d("getHomeNetworking", "${response.body()}")
            } else {
                Log.d("error", "getHomeNetworking : "+response.message())
            }
        }
    }
    fun getHomeUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getHomeUser()
            Log.d("getHomeUser", "$response")
            if (response.isSuccessful && response.body() != null) {
                (response.body()!!.result as ArrayList).removeIf{it.memberIdx == 22}
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
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getHomeProgram(memberIdx)
            Log.d("getHomeProgram", "$response")

            if (response.isSuccessful && response.body() != null) {
                _program.postValue(response.body())
                Log.d("getHomeProgram", "${response.body()}")
            } else {
                Log.d("error", "getHomeProgram : "+response.message())
            }
        }
    }

    fun getNotificationScroll(memberIdx : Int ,lastNotificationIdx : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getNotificationScroll(memberIdx, lastNotificationIdx)
            Log.d("getNotification", "$response")

            if (response.isSuccessful && response.body() != null) {
                _notificationScroll.postValue(response.body())
                Log.d("getNotification", "${response.body()}")
            }
            else {
                Log.d("error", "getNotification : "+response.message())
            }
        }
    }
    fun getNotification(memberIdx : Int) {
        viewModelScope.launch (Dispatchers.IO){
            val response = homeRepository.getNotification(memberIdx)
            Log.d("getNotification", "$response")

            if (response.isSuccessful && response.body() != null) {
                _notification.postValue(response.body())
                Log.d("getNotification", "${response.body()}")
            }
            else {
                Log.d("error", "getNotification : "+response.message())
            }
        }
    }

    fun getNotificationUnread(memberIdx: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getNotificationUnread(memberIdx)
            Log.d("getNotificationUnread", "$response")
            if (response.isSuccessful && response.body()?.result != null) {
                _notificationUnread.postValue(response.body())
                Log.d("getNotificationUnread", "${response.body()}")
            }
            else {
                Log.d("error", "getNotificationUnread : "+response.message())
            }
        }
    }
}