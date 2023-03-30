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

    val belong = MutableLiveData<String?>()
    init { belong.value = ""}

    val belongIsValid = MutableLiveData<Boolean>()
    init { belongIsValid.value = false}

    val email = MutableLiveData<String>()
    init { email.value = ""}

    val emailIsValid = MutableLiveData<Boolean>()
    init { emailIsValid.value = false}

    val intro = MutableLiveData<String?>()
    init { intro.value = ""}

    val introIsValid = MutableLiveData<Boolean>()
    init { introIsValid.value = false}

    val image = MutableLiveData<String?>()
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
    }

    //프로필 정보 편집
    private val _profileEdit = MutableLiveData<EditProfileDataResponse>()
    val profileEdit : LiveData<EditProfileDataResponse>
        get() = _profileEdit
    var img : MultipartBody.Part? = null

    fun getCheckEditProfileInfo(memberIdx : Int, img: MultipartBody.Part?) {
        var belongValue : String? = "\""+belong.value + "\""
        var introValue : String? = "\""+intro.value + "\""
        if(belong.value?.isEmpty() == true){
            belongValue = null
        }
        if(intro.value?.isEmpty() == true){
            introValue = null
        }
        var infoJson= JSONObject("{\"memberIdx\":\"${myMemberIdx}\",\"nickname\":\"${nickName.value.toString()}\",\"belong\":${belongValue},\"profileEmail\":\"${email.value.toString()}\",\"content\":${introValue}}").toString()

        val info = infoJson.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("image_success_edit", infoJson.toString())


        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getCheckEditProfileInfo(
                info,img)
            if (response.isSuccessful || response.body()?.result ?: null != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    Log.d("profile_response", response.body().toString())
                    _profileEdit.value = (response.body())
                }
            }
            else {
                Log.d("profile_response", response.message())
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

    val loadingSuccess = MutableLiveData<Int>()
    init { loadingSuccess.value = 0}

    fun getProfileInfo(memberIdx : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getProfileInfo(memberIdx)
            Log.d("profile_response", response.body().toString())
            Log.d("image_get", response.body().toString())

            if (response.isSuccessful || response.body()?.result ?: null != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    Log.d("success", response.message())
                    _profileInfo.value = (response.body())
                    loadingSuccess.value = loadingSuccess.value?.plus(1)
                }
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

            if (response.isSuccessful && response.body() != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _snsInfoArray.value = (response.body()?.result as ArrayList<SNSData>?)
                }
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

            if (response.isSuccessful && response.body()?.result != null) {
                //_networking.postValue(response.body())
                viewModelScope.launch(Dispatchers.Main) {
                    _educationInfoArray.value = response.body()?.result as ArrayList<EducationData>
                }
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

            if (response.isSuccessful && response.body()?.result != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    _careerInfoArray.value = (response.body()?.result as ArrayList<CareerData>)

                }

            }
            else {
                Log.d("error", response.message())
            }
        }
    }


}
