package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.NotificationRepository
import com.example.template.garamgaebi.model.NotificationResponse
import kotlinx.coroutines.launch

class NotificationViewModel: ViewModel() {
    private val notificationRepository = NotificationRepository()

    private val _notification = MutableLiveData<NotificationResponse>()
    val notification : LiveData<NotificationResponse>
        get() = _notification

    fun getNotification(memberIdx : Int) {
        viewModelScope.launch {
            val response = notificationRepository.getNotification(memberIdx)
            Log.d("getNotification", "$response")

            if (response.isSuccessful && response.body()?.result != null) {
                _notification.postValue(response.body())
                Log.d("getNotification", "${response.body()}")
            }
            else {
                Log.d("error", "getNotification : "+response.message())
            }
        }
    }
}