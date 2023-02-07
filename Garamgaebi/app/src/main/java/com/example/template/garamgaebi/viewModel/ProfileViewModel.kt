package com.example.template.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.model.*
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()

    //프로필 정보 조회
    private val _profileInfo = MutableLiveData<ProfileDataResponse>()
    val profileInfo : LiveData<ProfileDataResponse>
        get() = _profileInfo

    private val _myContent = MutableLiveData<String>()
    val myContent : LiveData<String>
        get() = _myContent

    fun getProfileInfo(memberIdx : Int) {
        viewModelScope.launch {
            val response = profileRepository.getProfileInfo(memberIdx)
            Log.d("present0", response.body().toString())

            if (response.isSuccessful || response.body()?.result ?: null != null) {
                Log.d("success", response.message())
                _profileInfo.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    //SNS 정보 조회
    private val _snsInfo = MutableLiveData<SNSDataResponse>()
    val snsInfo : LiveData<SNSDataResponse>
        get() = _snsInfo

    fun getSNSInfo(memberIdx : Int) {
        viewModelScope.launch {
            val response = profileRepository.getSNSInfo(memberIdx)
            Log.d("present", response.body().toString())

            if (response.isSuccessful) {
                _snsInfo.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    //교육 정보 조회
    private val _educationInfo = MutableLiveData<EducationDataResponse>()
    val educationInfo : LiveData<EducationDataResponse>
        get() = _educationInfo

    fun getEducationInfo(memberIdx : Int) {
        viewModelScope.launch {
            val response = profileRepository.getEducationInfo(memberIdx)
            Log.d("present", response.body().toString())

            if (response.isSuccessful) {
                _educationInfo.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    //경력 정보 조회
    private val _careerInfo = MutableLiveData<CareerDataResponse>()
    val careerInfo : LiveData<CareerDataResponse>
        get() = _careerInfo

    fun getCareerInfo(memberIdx : Int) {
        viewModelScope.launch {
            val response = profileRepository.getCareerInfo(memberIdx)
            Log.d("present", response.body().toString())

            if (response.isSuccessful) {
                _careerInfo.postValue(response.body())
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

//    //추천 프로필 정보 조회
//    private val _recommendInfo = MutableLiveData<RecommendProfileDataResponse>()
//    val recommendInfo : LiveData<RecommendProfileDataResponse>
//        get() = _recommendInfo
//
//    fun getRecommendProfile(memberIdx : Int) {
//        viewModelScope.launch {
//            val response = profileRepository.getRecommendProfile()
//            Log.d("present", response.body().toString())
//
//            if (response.isSuccessful) {
//                _recommendInfo.postValue(response.body())
//            }
//            else {
//                Log.d("error", response.message())
//            }
//        }
//    }

}
