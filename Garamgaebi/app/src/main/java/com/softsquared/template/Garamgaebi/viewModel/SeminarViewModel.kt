package com.softsquared.template.Garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsquared.template.Garamgaebi.src.main.seminar.data.PresentationResult
import com.softsquared.template.Garamgaebi.model.SeminarRepository
import com.softsquared.template.Garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse
import com.softsquared.template.Garamgaebi.src.main.seminar.data.SeminarParticipantsResponse
import com.softsquared.template.Garamgaebi.src.main.seminar.data.SeminarPresentResponse
import kotlinx.coroutines.launch

class SeminarViewModel : ViewModel(){
    private val seminarRepository = SeminarRepository()

    private val _presentation = MutableLiveData<SeminarPresentResponse>()
    val presentation : LiveData<SeminarPresentResponse>
    get() = _presentation

    private val _seminarParticipants = MutableLiveData<SeminarParticipantsResponse>()
    val seminarParticipants : LiveData<SeminarParticipantsResponse>
    get() = _seminarParticipants

    /*private val _info = MutableLiveData<SeminarDetailInfoResponse>()
    val info : LiveData<SeminarDetailInfoResponse>
    get() = _info*/

    fun getSeminarsInfo(seminaridx : Int) {
        viewModelScope.launch {
            val response = seminarRepository.getSeminarsInfo(1)
            Log.d("present", response.body().toString())
            if (!response.isSuccessful) {
                _presentation.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
           /*if (response.isSuccessful) {
                _presentation.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }*/
        }
    }
    fun getSeminarParticipants(seminaridx : Int) {
        viewModelScope.launch {
            val response = seminarRepository.getSeminarParticipants(1)
            Log.d("participants", response.body().toString())
            if (!response.isSuccessful) {
                _seminarParticipants.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
            /*if (response.isSuccessful) {
                _seminarParticipants.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }*/

        }
    }
}