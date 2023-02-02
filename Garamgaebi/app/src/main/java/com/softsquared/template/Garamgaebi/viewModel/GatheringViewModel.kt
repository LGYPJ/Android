package com.softsquared.template.Garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.template.Garamgaebi.model.GatheringProgramResponse
import com.softsquared.template.Garamgaebi.model.GatheringRepository
import kotlinx.coroutines.launch

class GatheringViewModel : ViewModel(){
    private val gatheringRepository = GatheringRepository()

    private val _programReady = MutableLiveData<GatheringProgramResponse>()
    val programReady : LiveData<GatheringProgramResponse>
    get() = _programReady

    private val _programClosed = MutableLiveData<GatheringProgramResponse>()
    val programClosed : LiveData<GatheringProgramResponse>
    get() = _programClosed

    fun getGatheringProgramReady(memberIdx : Int) {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringProgramReady(memberIdx)
            Log.d("gathering", response.body().toString())

            if (response.isSuccessful) {
                _programReady.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    fun getGatheringProgramClosed(memberIdx : Int) {
        viewModelScope.launch {
            val response = gatheringRepository.getGatheringProgramClosed(memberIdx)
            Log.d("gathering", response.body().toString())

            if (response.isSuccessful) {
                _programReady.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
}