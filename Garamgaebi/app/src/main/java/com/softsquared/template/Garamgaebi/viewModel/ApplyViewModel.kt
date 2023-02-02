package com.softsquared.template.Garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.template.Garamgaebi.model.*
import kotlinx.coroutines.launch

class ApplyViewModel : ViewModel() {
    private val applyRepository = ApplyRepository()

    private val _cancel = MutableLiveData<CancelResponse>()
    val cancel : LiveData<CancelResponse>
    get() = _cancel

    private val _enroll = MutableLiveData<EnrollResponse>()
    val enroll : LiveData<EnrollResponse>
    get() = _enroll

    fun postCancel(cancelRequest: CancelRequest) {
        viewModelScope.launch {
            val response = applyRepository.postCancel(cancelRequest)
            Log.d("cancel", response.body().toString())
            if(!response.isSuccessful){
                _cancel.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    fun postEnroll(enrollRequest: EnrollRequest){
        viewModelScope.launch {
            val response = applyRepository.postEnroll(enrollRequest)
            Log.d("enroll", response.body().toString())
            if(!response.isSuccessful){
                _enroll.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

}