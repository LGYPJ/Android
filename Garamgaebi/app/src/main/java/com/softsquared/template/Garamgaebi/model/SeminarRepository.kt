package com.softsquared.template.Garamgaebi.model

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication

class SeminarRepository {
    private val seminarClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun getSeminarsInfo(seminaridx : Int) = seminarClient.getSeminarsInfo(1)
}