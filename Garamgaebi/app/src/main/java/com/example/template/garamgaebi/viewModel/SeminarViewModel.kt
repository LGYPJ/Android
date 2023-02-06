package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.PresentationResult
import com.example.template.garamgaebi.model.SeminarRepository
import com.example.template.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResponse
import com.example.template.garamgaebi.src.main.seminar.data.SeminarPresentResponse
import kotlinx.coroutines.launch

class SeminarViewModel : ViewModel(){
    private val seminarRepository = SeminarRepository()

    private val _presentation = MutableLiveData<SeminarPresentResponse>()
    val presentation : LiveData<SeminarPresentResponse>
    get() = _presentation

    private val _present = MutableLiveData<ArrayList<PresentationResult>>()
    val present : LiveData<ArrayList<PresentationResult>>
    get() = _present

    private val _seminarParticipants = MutableLiveData<SeminarParticipantsResponse>()
    val seminarParticipants : LiveData<SeminarParticipantsResponse>
    get() = _seminarParticipants

    private val _info = MutableLiveData<SeminarDetailInfoResponse>()
    val info : LiveData<SeminarDetailInfoResponse>
    get() = _info

    fun getSeminarsInfo(seminaridx : Int) {
        viewModelScope.launch {
            val response = seminarRepository.getSeminarsInfo(6)
            Log.d("seminarPresent", response.body().toString())
            if (response.isSuccessful) {
                //_presentation.postValue(response.body())
                _present.postValue(response.body()?.result as ArrayList<PresentationResult>?)
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
    fun getSeminarParticipants(seminaridx : Int, memberIdx: Int) {
        viewModelScope.launch {
            val response = seminarRepository.getSeminarParticipants(6,1)
            Log.d("seminarParticipants", response.body().toString())
            if (response.isSuccessful) {
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

    fun getSeminarDetail(memberIdx: Int,programIdx: Int) {
        viewModelScope.launch {
            val response = seminarRepository.getSeminarDetail(0,0)
            Log.d("seminarDetail", response.body().toString())
            if(!response.isSuccessful){
                _info.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
            /*if(response.isSuccessful){
                _info.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }*/
        }
    }
}