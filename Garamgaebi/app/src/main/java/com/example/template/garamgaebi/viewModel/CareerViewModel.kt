package com.example.template.garamgaebi.viewModel

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.launch

class CareerViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
    var addressFirst : Boolean = false
    var typeFirst: Boolean = false
    var careerIdx:Int = -1

    val company = MutableLiveData<String>()
    init { company.value = ""}

    val companyIsValid = MutableLiveData<Boolean>()
    init { companyIsValid.value = false}

    val position = MutableLiveData<String>()
    init { position.value = ""}

    val positionIsValid = MutableLiveData<Boolean>()
    init { positionIsValid.value = false}

    val startDate = MutableLiveData<String>()
    init { startDate.value = ""}

    val endDate = MutableLiveData<String>()
    init { endDate.value = ""}

    val startDateIsValid = MutableLiveData<Boolean>()
    init { startDateIsValid.value = false}

    val endDateIsValid = MutableLiveData<Boolean>()
    init { endDateIsValid.value = false}

    val isWorking = MutableLiveData<String>()
    init { isWorking.value = "FALSE"}

    private val _add = MutableLiveData<AddCareerDataResponse>()
    val add : LiveData<AddCareerDataResponse>
        get() = _add
    //Career 추가
    fun postCareerInfo() {
        viewModelScope.launch {
            val response = profileRepository.getCheckAddCareer(AddCareerData(myMemberIdx,company.value.toString(), position.value.toString(), isWorking.value.toString(),
            startDate.value.toString(), endDate.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _add.postValue(response.body())
                Log.d("career_add_success", response.toString() +endDate.value.toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _patch = MutableLiveData<BooleanResponse>()
    val patch : LiveData<BooleanResponse>
        get() = _patch
    //경력 수정
    fun patchCareerInfo() {
        viewModelScope.launch {
            val response = profileRepository.patchCareer(CareerData(careerIdx,company.value.toString(), position.value.toString(), isWorking.value.toString(),
                startDate.value.toString(), endDate.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _patch.postValue(response.body())
                Log.d("career_patch_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _delete = MutableLiveData<BooleanResponse>()
    val delete : LiveData<BooleanResponse>
        get() = _delete
    //경력 삭제
    fun deleteCareerInfo() {
        viewModelScope.launch {
            val response = profileRepository.deleteCareer(careerIdx)
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _delete.postValue(response.body())
                Log.d("career_delete_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
}
