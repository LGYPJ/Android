package com.softsquared.template.Garamgaebi.src.seminar.data

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication
import retrofit2.create

class SeminarRepository {
    private val seminarClient = GaramgaebiApplication.sRetrofit.create(SeminarInterface::class.java)

    suspend fun getSeminarsInfo(seminaridx : Int) = seminarClient.getSeminarsInfo(1)
}