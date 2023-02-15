package com.garamgaebi.garamgaebi.garamgaebi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garamgaebi.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.garamgaebi.repository.ProfileRepository
import kotlinx.coroutines.launch

class ServiceCenterViewModel : ViewModel(){
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

    private val _qna = MutableLiveData<QnADataResponse>()
    val qna : LiveData<QnADataResponse>
        get() = _qna
    //QnA 문의
    fun postQna() {
        viewModelScope.launch {
            val response = profileRepository.getCheckQnA(QnAData(1,email.value.toString(), category.value.toString(), content.value.toString()))
            //Log.d("sns_add", response.body().toString())
            if(response.isSuccessful){
                _qna.postValue(response.body())
                Log.d("QnA_success", response.toString())
            }
            else {
                //response.body()?.message?.let { Log.d("error", it) }
            }
        }
    }
}
