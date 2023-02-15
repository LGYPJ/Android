package com.garamgaebi.garamgaebi.common

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import java.util.*


class GaramgaebiFunction {
    //날짜 데이터 변환
    fun getDate(realDate : String) : String {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val date = LocalDateTime.parse(realDate, pattern)
        val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분", Locale.KOREA)
        return if (date.format(pattern2)!!.contains("00분")) {
            val pattern3 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시", Locale.KOREA)
            date.format(pattern3)
        } else {
            date.format(pattern2)
        }
    }

    //editText focus Listener
//    var onFocusChangeListener =
//        OnFocusChangeListener { view, isFocused ->
//            val origin = view.background
//            if(isFocused){
//                view.setBackgroundResource(R.drawable.basic_black_border_layout)
//            }else{
//                view.background = origin
//            }
//        }
    interface OnFocusLostListener {
        fun onFocusLost(view: EditText,boolean: Boolean)
    }
    interface OnFocusingListener {
        fun onFocusing(boolean: Boolean)
    }
    fun logTest(view:EditText, check : Boolean,isValid : Boolean){
        Log.d("focus_check_log",view.toString() + check.toString() + isValid.toString())
    }
    companion object {
        @JvmStatic
        fun setBoolean(
            data: MutableLiveData<Boolean>,
            first: MutableLiveData<Boolean>,
            check: Boolean
        ) {
            data.value = check
            first.value = false

        }
    }


    fun getDateYMD(beforeDate : String) : String {
        val date = LocalDateTime.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getDateMyMeeting(beforeDate : String) : String {
        val date = LocalDateTime.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return date.format(DateTimeFormatter.ofPattern("M월 dd일\n\nh:00", Locale.KOREA))
    }

    fun getDDay(beforeDate : String) : String {
        val date = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        Log.d("dday", "$date")
        val now = LocalDate.now()
        Log.d("dday", "$now")
        Log.d("dday", "${ChronoUnit.DAYS.between(date, now)}")
        return if(ChronoUnit.DAYS.between(date, now) == 0L) "D-Day"
        else "D${ChronoUnit.DAYS.between(date, now)}"
    }

    //현재인지 확인
    fun checkNow(inputDate :String) : Boolean {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        var formatted = current.format(formatter)
        return inputDate >= formatted
    }

    // 아이스브레이킹 참가하기 버튼 활성화
    fun checkIceBreaking(startDate : String):Boolean {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var formatted = current.format(formatter)
        return formatted == startDate
    }



}