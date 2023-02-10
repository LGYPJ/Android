package com.example.template.garamgaebi.common


import android.util.Log
import com.example.template.garamgaebi.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import java.util.*

class GaramgaebiFunction {
    fun getDate(realDate : String):String {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val date = LocalDateTime.parse(realDate, pattern)
        val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분", Locale.KOREA)
        return if(date.format(pattern2)!!.contains("00분")){
            val pattern3 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시", Locale.KOREA)
            date.format(pattern3)
        } else{
            date.format(pattern2)
        }
    }

    fun getDateYMD(beforeDate : String) : String {
        val date = LocalDateTime.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA))
    }

    fun getDateHomeMyMeeting(beforeDate : String) : String {
        val date = LocalDateTime.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return date.format(DateTimeFormatter.ofPattern("M월 dd일\nh:00", Locale.KOREA))
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
    //날짜 데이터 변환
    fun getDate(realDate : String):String {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val date = LocalDateTime.parse(realDate, pattern)
        val pattern2 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분", Locale.KOREA)
        return if(date.format(pattern2)!!.contains("00분")){
            val pattern3 = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시", Locale.KOREA)
            date.format(pattern3)
        } else{
            date.format(pattern2)
        }
    }

}