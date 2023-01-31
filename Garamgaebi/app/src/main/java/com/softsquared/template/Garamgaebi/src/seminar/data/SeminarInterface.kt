package com.softsquared.template.Garamgaebi.src.seminar.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeminarInterface {
    @GET("/seminars/{seminar-idx}/presentations")
    suspend fun getSeminarsInfo(@Path("seminar-idx") seminaridx: Int) : Response<SeminarPresentResponse>
}