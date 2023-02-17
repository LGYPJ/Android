package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.launch

class EducationViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
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

    /*유효성 검사를 위한 부분
    * 1) 교육기관
    * 2) 전공
    * 3) 시작년월
    * 4) 종료년월
    * * */

    //포커싱 감지
    val institutionFocusing = MutableLiveData<Boolean>(false)
    val majorFocusing = MutableLiveData<Boolean>(false)
    val startFocusing = MutableLiveData<Boolean>(false)
    val endFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var institutionFirst = MutableLiveData<Boolean>(true)
    var majorFirst = MutableLiveData<Boolean>(true)
    var startFirst = MutableLiveData<Boolean>(true)
    var endFirst = MutableLiveData<Boolean>(true)

    //hint 문구
    var institutionHint= MutableLiveData<String>("")
    var majorHint = MutableLiveData<String>("")
    var startHint = MutableLiveData<String>("")
    var endHint = MutableLiveData<String>("")

    //checkBox
    var checkBox = MutableLiveData<Boolean>(false)

    //유효성 문구
    var institutionState = MutableLiveData<String>("")
    var majorState = MutableLiveData<String>("")
    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        Log.d("링크 focusing입니다",data.value.toString())
        Log.d("링크 첫입력입니다",first.value.toString())
        Log.d("링크 시작 첫입력입니다",startFirst.value.toString())
        Log.d("링크 종료 첫입력입니다",endFirst.value.toString())

    }

    var showStart = MutableLiveData<Boolean>(false)
    var showEnd = MutableLiveData<Boolean>(false)
    fun showDatePicker(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,show:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        show.value = true
    }


    //유효성 끝

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
