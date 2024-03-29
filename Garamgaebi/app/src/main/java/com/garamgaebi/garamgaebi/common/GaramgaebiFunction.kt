package com.garamgaebi.garamgaebi.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
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

    fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    object ImageLoader {
        suspend fun loadImage(imageUrl: String): Bitmap? {
            val bmp: Bitmap? = null
            try {
                val url = URL(imageUrl)
                val stream = withContext(Dispatchers.IO) {
                    url.openStream()
                }

                return BitmapFactory.decodeStream(stream)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bmp
        }
    }

    fun checkFirstChar(isValid: MutableLiveData<Boolean>, it:String){
        if(it.isNotEmpty()){
            if(it.toCharArray()[0] == ' ')
                isValid.value = false
            if(it.contains('\n'))
                isValid.value = false
        }
    }

    interface OnFocusingListener {
        fun onFocusing(boolean: Boolean)
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
        return date.format(DateTimeFormatter.ofPattern("M월 dd일", Locale.KOREA))
    }
    fun getTimeMyMeeting(beforeDate : String) : String {
        val date = LocalDateTime.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        return date.format(DateTimeFormatter.ofPattern("h:mm", Locale.KOREA))
    }
    fun getDDay(beforeDate : String) : String {
        val date = LocalDate.parse(beforeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        val now = LocalDate.now()
        return if(ChronoUnit.DAYS.between(date, now) == 0L) "D-Day"
        else "D${ChronoUnit.DAYS.between(date, now)}"
    }

    //현재인지 확인
    fun checkNow(inputDate :String) : Boolean {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM")
        val formatted = current.format(formatter)
        return inputDate >= formatted
    }

    // 아이스브레이킹 참가하기 버튼 활성화
    fun checkIceBreaking(startDate : String):Boolean {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        return formatted == startDate
    }

}