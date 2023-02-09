package com.example.template.garamgaebi.common

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class GaramgaebiFunction {
    fun getDay(date : String): String{
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd")).toString()
    }

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