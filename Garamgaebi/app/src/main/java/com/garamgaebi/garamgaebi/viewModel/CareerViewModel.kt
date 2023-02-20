package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ProfileRepository
import com.garamgaebi.garamgaebi.src.main.profile.DatePickerDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CareerViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
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


    /*유효성 검사를 위한 부분
    * 1) 회사
    * 2) 직함
    * 3) 시작년월
    * 4) 종료년월
    * * */

    //포커싱 감지
    val companyFocusing = MutableLiveData<Boolean>(false)
    val positionFocusing = MutableLiveData<Boolean>(false)
    val startFocusing = MutableLiveData<Boolean>(false)
    val endFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var companyFirst = MutableLiveData<Boolean>(true)
    var positionFirst = MutableLiveData<Boolean>(true)
    var startFirst = MutableLiveData<Boolean>(true)
    var endFirst = MutableLiveData<Boolean>(true)

    //hint 문구
    var companyHint= MutableLiveData<String>("")
    var positionHint = MutableLiveData<String>("")


    //checkBox
    var checkBox = MutableLiveData<Boolean>(false)

    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false

    }
    var showStart = MutableLiveData<Boolean>(false)
    var showEnd = MutableLiveData<Boolean>(false)
    fun showDatePicker(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,show:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        show.value = true
    }

    //유효성 끝

    private val _add = MutableLiveData<AddCareerDataResponse>()
    val add : LiveData<AddCareerDataResponse>
        get() = _add
    //Career 추가
    fun postCareerInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getCheckAddCareer(AddCareerData(myMemberIdx,company.value.toString(), position.value.toString(), isWorking.value.toString(),
            startDate.value.toString(), endDate.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _add.value = response.body()
                }
                Log.d("career_add_success", response.toString() + "마지막날"+endDate.value.toString())
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
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.patchCareer(CareerData(careerIdx,company.value.toString(), position.value.toString(), isWorking.value.toString(),
                startDate.value.toString(), endDate.value.toString()))
            Log.d("career_patch_success", isWorking.value.toString())
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _patch.value = response.body()
                }
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
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.deleteCareer(careerIdx)
            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _delete.value = response.body()
                }
                Log.d("career_delete_success", response.body().toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
}
