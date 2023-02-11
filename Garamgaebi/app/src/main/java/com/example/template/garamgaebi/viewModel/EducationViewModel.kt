package com.example.template.garamgaebi.viewModel

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import com.example.template.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.launch

class EducationViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
    var addressFirst : Boolean = false
    var typeFirst: Boolean = false

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
            val response = profileRepository.getCheckAddEducation(EducationData(1, institution.value.toString(), major.value.toString(), isLearning.value.toString(),
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
}
