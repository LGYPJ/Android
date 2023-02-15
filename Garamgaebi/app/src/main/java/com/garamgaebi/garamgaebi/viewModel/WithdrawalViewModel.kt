package com.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.repository.ProfileRepository

class WithdrawalViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()

    val email = MutableLiveData<String>()
    init { email.value = ""}

    val emailIsValid = MutableLiveData<Boolean>()
    init { emailIsValid.value = false}

    val content = MutableLiveData<String>()
    init { content.value = ""}

    val contentIsValid = MutableLiveData<Boolean>()
    init { contentIsValid.value = false}

    val category = MutableLiveData<String>()
    init { category.value = ""}

    val categoryIsValid = MutableLiveData<Boolean>()
    init { categoryIsValid.value = false}

    val agree = MutableLiveData<Boolean>()
    init { agree.value = false}

    val agreeIsValid = MutableLiveData<Boolean>()
    init { agreeIsValid.value = false}

    /*유효성 검사를 위한 부분
   * 1) 이메일
   * 2) 문의사유
   * 3) 내용
   * 4) 동의
   * * */

    //포커싱 감지
    val categoryFocusing = MutableLiveData<Boolean>(false)
    val contentFocusing = MutableLiveData<Boolean>(false)

    //첫 입력 확인
    var categoryFirst = MutableLiveData<Boolean>(true)
    var contentFirst = MutableLiveData<Boolean>(true)

    //hint 문구
    var categoryHint = MutableLiveData<String>("")
    var contentHint = MutableLiveData<String>("")

    //checkBox
    var checkBox = MutableLiveData<Boolean>(false)

    //유효성 문구
    var emailState = MutableLiveData<String>("")
    fun setBoolean(data:MutableLiveData<Boolean>,first:MutableLiveData<Boolean>,check : Boolean){
        data.value = check
        first.value = false
        Log.d("링크 focusing입니다",data.value.toString())
        Log.d("링크 첫입력입니다",first.value.toString())
    }


    private val _withdrawal = MutableLiveData<QnADataResponse>()
    val withdrawal : LiveData<QnADataResponse>
        get() = _withdrawal

    //회원탈퇴 문의
    fun postWithdarwal() {
//        viewModelScope.launch {
//            val response = profileRepository.getWi(QnAData(1,email.value.toString(), category.value.toString(), content.value.toString()))
//            //Log.d("sns_add", response.body().toString())
//            if(response.isSuccessful){
//                _withdrawal.postValue(response.body())
//                Log.d("QnA_success", response.toString())
//            }
//            else {
//                //response.body()?.message?.let { Log.d("error", it) }
//            }
//        }
    }
}
