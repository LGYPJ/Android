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

class WithdrawalViewModel : ViewModel(){
    private val profileRepository = ProfileRepository()
    var addressFirst : Boolean = false
    var typeFirst: Boolean = false



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
