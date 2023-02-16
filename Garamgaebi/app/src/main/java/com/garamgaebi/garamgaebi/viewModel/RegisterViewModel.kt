package com.garamgaebi.garamgaebi.viewModel


import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.model.RegisterEmailResponse
import com.garamgaebi.garamgaebi.model.RegisterEmailVerifyRequest
import com.garamgaebi.garamgaebi.model.RegisterSendEmailRequest
import com.garamgaebi.garamgaebi.repository.RegisterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel : ViewModel(){
    private val registerRepository = RegisterRepository()

    val socialEmail = MutableLiveData<String>("")
    val uniEmail = MutableLiveData<String>("")
    val authNum = MutableLiveData<String>("")
    val emailSent = MutableLiveData<String>("")
    val authNumSent = MutableLiveData<String>("")
    val nickname = MutableLiveData<String>("")
    val profileEmail = MutableLiveData<String>("")
    val company = MutableLiveData<String>("")
    val position = MutableLiveData<String>("")
    val careerStartDate = MutableLiveData<String>("")
    val careerEndDate = MutableLiveData<String>("")
    val careerCheckBox = MutableLiveData<Boolean>(false)
    val institution = MutableLiveData<String>("")
    val major = MutableLiveData<String>("")
    val eduStartDate = MutableLiveData<String>("")
    val eduEndDate = MutableLiveData<String>("")
    val eduCheckBox = MutableLiveData<Boolean>(false)

    val emailFocusing = MutableLiveData<Boolean>(false)
    val authNumFocusing = MutableLiveData<Boolean>(false)
    val nicknameFocusing = MutableLiveData<Boolean>(false)
    val profileEmailFocusing = MutableLiveData<Boolean>(false)
    val companyFocusing = MutableLiveData<Boolean>(false)
    val positionFocusing = MutableLiveData<Boolean>(false)
    val careerStartDateFocusing = MutableLiveData<Boolean>(false)
    val careerEndDateFocusing = MutableLiveData<Boolean>(false)
    val institutionFocusing = MutableLiveData<Boolean>(false)
    val majorFocusing = MutableLiveData<Boolean>(false)
    val eduStartDateFocusing = MutableLiveData<Boolean>(false)
    val eduEndDateFocusing = MutableLiveData<Boolean>(false)


    val timerFirst = MutableLiveData<Boolean>(true)
    val isTimerRunning = MutableLiveData<Boolean>(false)


    val isEmailValid = MutableLiveData<Boolean>(false)
    val isNumValid = MutableLiveData<Boolean>(false)
    val isAuthWrong = MutableLiveData<Boolean>(false)
    val isCompleteAuth = MutableLiveData<Boolean>(false)
    val isNickNameValid = MutableLiveData<Boolean>(false)
    val isProfileEmailValid = MutableLiveData<Boolean>(false)
    val isCompanyValid = MutableLiveData<Boolean>(false)
    val isPositionValid = MutableLiveData<Boolean>(false)
    val isCareerStartDateValid = MutableLiveData<Boolean>(false)
    val isCareerEndDateValid = MutableLiveData<Boolean>(false)
    val isInstitutionValid = MutableLiveData<Boolean>(false)
    val isMajorValid = MutableLiveData<Boolean>(false)
    val isEduStartDateValid = MutableLiveData<Boolean>(false)
    val isEduEndDateValid = MutableLiveData<Boolean>(false)
    val isWorking = MutableLiveData<String>("")
    val isLearning = MutableLiveData<String>("")

    var timerCount = MutableLiveData<Int>(180)
    lateinit var timer : Job


    private val _emailVerify = MutableLiveData<RegisterEmailResponse>()
    val emailVerify  : LiveData<RegisterEmailResponse>
        get() = _emailVerify

    private val _sendEmail = MutableLiveData<RegisterEmailResponse>()
    val sendEmail  : LiveData<RegisterEmailResponse>
        get() = _sendEmail



    fun postEmailVerify(request : RegisterEmailVerifyRequest) {
        viewModelScope.launch {
            val response = registerRepository.postEmailVerify(request)
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _emailVerify.postValue(response.body())
                Log.d("postEmailConfirm", "${response.body()}")
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
    fun postSendEmail(request: RegisterSendEmailRequest) {
        viewModelScope.launch {
            val response = registerRepository.postSendEmail(request)
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _sendEmail.postValue(response.body())
                Log.d("postEmailConfirm", "${response.body()}")
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }

    fun timerStart() {
        if(::timer.isInitialized) timer.cancel()
        isTimerRunning.value = true
        timerCount.value = 180
        timerFirst.value = false
        timer = viewModelScope.launch {
            while(timerCount.value!! > 0) {
                timerCount.value = timerCount.value!!.minus(1)
                delay(1000)
            }
            isTimerRunning.value = false
        }
    }
    fun setBoolean(data:MutableLiveData<Boolean>, check : Boolean){
        data.value = check
        Log.d("focus", "$data ${data.value}")
    }

    fun checkEmail(): Boolean {
        val validation = "^[a-zA-Z0-9]{5,20}$"
        return Pattern.matches(validation, uniEmail.value)
    }
    fun checkAuthNum() : Boolean {
        val validation = "^[0-9]{6}$"
        return Pattern.matches(validation, authNum.value)
    }
    fun getEmail(context: Context) : String {
        Log.d("getEmail", "${uniEmail.value+context.getString(R.string.register_email_gachon)}")
        return uniEmail.value+context.getString(R.string.register_email_gachon)
    }
    fun checkNickname() : Boolean {
        val validation = "^[a-zA-Z0-9가-힣]{1,8}$"
        Log.d("checkNickname", "${Pattern.matches(validation, nickname.value)}")
        return Pattern.matches(validation, nickname.value)
    }
    fun checkProfileEmail() : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(profileEmail.value).matches()
    }
}