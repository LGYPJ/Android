package com.softsquared.template.Garamgaebi.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeminarInterface {
    @GET("/seminars/{seminar-idx}/presentations")
    suspend fun getSeminarsInfo(@Path("seminar-idx") seminaridx: Int) : Response<SeminarPresentResponse>
}