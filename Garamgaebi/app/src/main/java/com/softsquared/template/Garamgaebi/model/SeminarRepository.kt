package com.softsquared.template.Garamgaebi.model

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication

class SeminarRepository {
    private val seminarClient = GaramgaebiApplication.sRetrofit.create(SeminarInterface::class.java)

    suspend fun getSeminarsInfo(seminaridx : Int) = seminarClient.getSeminarsInfo(1)
}