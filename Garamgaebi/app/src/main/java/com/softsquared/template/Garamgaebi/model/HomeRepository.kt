package com.softsquared.template.Garamgaebi.model

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication.Companion.sRetrofit

class HomeRepository {
    private val homeClient = sRetrofit.create(ApiInterface::class.java)

    suspend fun getHomeSeminar() = homeClient.getHomeSeminar()
    suspend fun getHomeNetworking() = homeClient.getHomeNetworking()
    suspend fun getHomeUser() = homeClient.getHomeUser()
    suspend fun getHomeProgram(memberIdx : Int) = homeClient.getHomeProgram(1)
}