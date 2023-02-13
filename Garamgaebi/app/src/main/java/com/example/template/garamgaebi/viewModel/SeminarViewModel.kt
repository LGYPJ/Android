package com.example.template.garamgaebi.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.sSharedPreferences
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.repository.SeminarRepository
import com.example.template.garamgaebi.src.main.seminar.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SeminarViewModel : ViewModel(){
    private val seminarRepository = SeminarRepository()

    private val _presentation = MutableLiveData<SeminarPresentResponse>()
    val presentation : LiveData<SeminarPresentResponse>
    get() = _presentation

    /*private val _present = MutableLiveData<ArrayList<PresentationResult>>()
    val present : LiveData<ArrayList<PresentationResult>>
    get() = _present*/

    private val _seminarParticipants = MutableLiveData<List<SeminarResult>>()
    val seminarParticipants : LiveData<List<SeminarResult>>
    get() = _seminarParticipants

    private val _info = MutableLiveData<SeminarDetailInfoResponse>()
    val info : LiveData<SeminarDetailInfoResponse>
    get() = _info

    //val pay : MutableLiveData<String> = MutableLiveData("무료")
    fun getSeminarsInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = seminarRepository.getSeminarsInfo(sSharedPreferences.getInt("programIdx", 0))
            Log.d("seminarPresent", response.body().toString())
            if (response.isSuccessful) {
                _presentation.postValue(response.body())
                //_present.postValue(response.body()?.result as ArrayList<PresentationResult>?)
            }
            else {
                Log.d("error", response.message())
            }
        }
    }
    fun getSeminarParticipants() {
        viewModelScope.launch (Dispatchers.IO){
            val response = seminarRepository.getSeminarParticipants(sSharedPreferences.getInt("programIdx", 0), sSharedPreferences.getInt("memberIdx", 0))
            Log.d("seminarParticipants", response.body().toString())
            if (response.isSuccessful) {
                _seminarParticipants.postValue(response.body()?.result?.participantList)
            }
            else {
                Log.d("error", response.message())
            }
        }
    }

    fun getSeminarDetail() {
        viewModelScope.launch (Dispatchers.IO){
            val response = seminarRepository.getSeminarDetail(sSharedPreferences.getInt("programIdx", 0), sSharedPreferences.getInt("memberIdx", 0))
            Log.d("seminarDetail", response.body().toString())
            if(response.isSuccessful) {
                //date 가공
                /*val time = response.body()?.result?.date
                val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                val date = LocalDateTime.parse(time, pattern)
                val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분", Locale.KOREA)
                if(date.format(pattern2)!!.contains("00분")){
                    val pattern3 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시", Locale.KOREA)
                    response.body()?.result?.date = date.format(pattern3)
                }
                else{
                    response.body()?.result?.date = date.format(pattern2)
                }

                //endDate 가공
                val endTime = response.body()?.result?.endDate
                val patternEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                val dateEnd = LocalDateTime.parse(endTime, patternEnd)
                val pattern2End = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분", Locale.KOREA)
                if(date.format(pattern2End)!!.contains("00분")){
                    val pattern3End = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시", Locale.KOREA)
                    response.body()?.result?.endDate = dateEnd.format(pattern3End)
                }
                else{
                    response.body()?.result?.endDate = dateEnd.format(pattern2End)
                }*/
                //날짜 데이터 변환
                response.body()?.result?.date =
                    response.body()?.result?.date?.let { GaramgaebiFunction().getDate(it) }.toString()
                response.body()?.result?.endDate =
                    response.body()?.result?.endDate?.let { GaramgaebiFunction().getDate(it) }.toString()

                _info.postValue(response.body())
            }
            else{
                Log.d("error", response.message())
            }
        }
    }

    /*fun feeFree(money : String): String {
        return pay.value.toString()
    }

    fun convertFee(money : String):String{
        return "$money 원"
    }*/

    /*fun convertDate(date: String?): String? {
        val dateFormat = "yyyy-MM-dd hh:mm '시'"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return simpleDateFormat.format(date)
    }*/
}