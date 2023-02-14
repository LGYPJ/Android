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

class EducationViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
    var addressFirst : Boolean = false
    var typeFirst: Boolean = false
    var educationIdx:Int = -1

    val institution = MutableLiveData<String>()
    init { institution.value = ""}

    val institutionIsValid = MutableLiveData<Boolean>()
    init { institutionIsValid.value = false}

    val major = MutableLiveData<String>()
    init { major.value = ""}

    val majorIsValid = MutableLiveData<Boolean>()
    init { majorIsValid.value = false}

    val startDate = MutableLiveData<String>()
    init { startDate.value = ""}

    val endDate = MutableLiveData<String>()
    init { endDate.value = ""}

    val startDateIsValid = MutableLiveData<Boolean>()
    init { startDateIsValid.value = false}

    val endDateIsValid = MutableLiveData<Boolean>()
    init { endDateIsValid.value = false}

    val isLearning = MutableLiveData<String>()
    init { isLearning.value = "FALSE"}

    private val _add = MutableLiveData<AddEducationDataResponse>()
    val add : LiveData<AddEducationDataResponse>
        get() = _add

    //Education 추가
    fun postEducationInfo() {
        viewModelScope.launch {
            val response = profileRepository.getCheckAddEducation(AddEducationData(myMemberIdx, institution.value.toString(), major.value.toString(), isLearning.value.toString(),
            startDate.value.toString(), endDate.value.toString()))
            Log.d("education_add", response.toString())
            if(response.isSuccessful){
                _add.postValue(response.body())
                Log.d("education_add_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _patch = MutableLiveData<BooleanResponse>()
    val patch : LiveData<BooleanResponse>
        get() = _patch
    //교육 수정
    fun patchEducationInfo() {
        viewModelScope.launch {
            val response = profileRepository.patchEducation(EducationData(educationIdx, institution.value.toString(), major.value.toString(), isLearning.value.toString(),
                startDate.value.toString(), endDate.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _patch.postValue(response.body())
                Log.d("education_patch_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    private val _delete = MutableLiveData<BooleanResponse>()
    val delete : LiveData<BooleanResponse>
        get() = _delete
    //교육 삭제
    fun deleteEducationInfo() {
        viewModelScope.launch {
            val response = profileRepository.deleteEducation(educationIdx)
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _delete.postValue(response.body())
                Log.d("education_delete_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
}
