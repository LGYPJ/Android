package com.example.template.garamgaebi.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GaramgaebiFunction {
    fun getDay(date : String): String{
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd")).toString()
    }
}