package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.HttpCookie.parse

class ProfileViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()

    //프로필 확인

    val nickName = MutableLiveData<String>()
    init { nickName.value = ""}

    val nickNameIsValid = MutableLiveData<Boolean>()
    init { nickNameIsValid.value = false}

    val belong = MutableLiveData<String>()
    init { belong.value = ""}

    val belongIsValid = MutableLiveData<Boolean>()
    init { belongIsValid.value = false}

    val email = MutableLiveData<String>()
    init { email.value = ""}

    val emailIsValid = MutableLiveData<Boolean>()
    init { emailIsValid.value = false}

    val intro = MutableLiveData<String>()
    init { intro.value = ""}

    val introIsValid = MutableLiveData<Boolean>()
    init { introIsValid.value = false}

    val image = MutableLiveData<String>()
    init{image.value = ""}

    val imageIsValid = MutableLiveData<Boolean>()
    init { imageIsValid.value = false}

    /*유효성 검사를 위한 부분
  * 1) 닉네임
  * 2) 한 줄 소개
  * 3) 이메일
  * 4) 자기소개
  * * */

    //포커싱 감지
    val nameFocusing = MutableLiveData<Boolean>(false)
    val oneLineFocusing = MutableLiveData<Boolean>(false)
    val emailFocusing = MutableLiveData<Boolean>(false)
    val introFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var nameFirst = MutableLiveData<Boolean>(true)
    var oneLineFirst = MutableLiveData<Boolean>(true)
    var emailFirst = MutableLiveData<Boolean>(true)
    var introFirst = MutableLiveData<Boolean>(true)

    //hint 문구
    var nameHint= MutableLiveData<String>("")
    var oneLineHint = MutableLiveData<String>("")
    var emailHint = MutableLiveData<String>("")
    var introHint = MutableLiveData<String>("")

    //유효성 문구
    var nameState = MutableLiveData<String>("")
    var oneLineState = MutableLiveData<String>("")
    var emailState = MutableLiveData<String>("")
    var introState = MutableLiveData<String>("")

    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        Log.d("링크 focusing입니다",data.value.toString())
        Log.d("링크 첫입력입니다",first.value.toString())
    }

    //프로필 정보 편집
    private val _profileEdit = MutableLiveData<EditProfileDataResponse>()
    val profileEdit : LiveData<EditProfileDataResponse>
        get() = _profileEdit
    var img : MultipartBody.Part? = null

    fun getCheckEditProfileInfo(memberIdx : Int, img: MultipartBody.Part?) {
        val infoJson= JSONObject("{\"memberIdx\":\"${myMemberIdx}\",\"nickname\":\"${nickName.value.toString()}\",\"belong\":\"${belong.value.toString()}\",\"profileEmail\":\"${email.value.toString()}\",\"content\":${intro.value.toString()}}").toString()
//        val info = RequestBody.create("application/json".toMediaTypeOrNull(),infoJson)
        val info = infoJson.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("img_edit2", infoJson.toString())
        Log.d("img_edit2", info.toString())


        viewModelScope.launch {
//            val response = profileRepository.getCheckEditProfileInfo(
//                EditProfileInfoData(myMemberIdx,nickName.value.toString(),belong.value.toString(),email.value.toString(),intro.value.toString()
//                ),img)
            val response = profileRepository.getCheckEditProfileInfo(
                info,img)
            Log.d("present_edit", response.body().toString())

            if (response.isSuccessful || response.body()?.result ?: null != null) {
                Log.d("success_edit", response.message())
                _profileEdit.postValue(response.body())
            }
            else {
                Log.d("error_edit", response.message())
            }
        }
    }

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

    private val _snsInfoArray = MutableLiveData<ArrayList<SNSData>>()
    val snsInfoArray : LiveData<ArrayList<SNSData>>
        get() = _snsInfoArray

    fun getSNSInfo(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getSNSInfo(memberIdx)
            Log.d("api_sns", response.body().toString())

            if (response.isSuccessful) {
                //_snsInfo.postValue(response.body())
                _snsInfoArray.postValue(response.body()?.result as ArrayList<SNSData>?)
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

    private val _educationInfoArray = MutableLiveData<ArrayList<EducationData>>()
    val educationInfoArray : LiveData<ArrayList<EducationData>>
        get() = _educationInfoArray

    fun getEducationInfo(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getEducationInfo(memberIdx)
            Log.d("api_edu", response.body().toString())

            if (response.isSuccessful) {
                _educationInfoArray.postValue(response.body()?.result as ArrayList<EducationData>)
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

    private val _careerInfoArray = MutableLiveData<ArrayList<CareerData>>()
    val careerInfoArray : LiveData<ArrayList<CareerData>>
        get() = _careerInfoArray

    fun getCareerInfo(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getCareerInfo(memberIdx)
            Log.d("api_career", response.body().toString())

            if (response.isSuccessful) {
                _careerInfoArray.postValue(response.body()?.result as ArrayList<CareerData>)
            }
            else {
                Log.d("error", response.message())
            }
        }
    }


}
