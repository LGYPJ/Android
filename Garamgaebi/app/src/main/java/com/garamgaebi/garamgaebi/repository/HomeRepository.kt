package com.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.sRetrofit
import com.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.model.LoginRequest

class HomeRepository {
    private val homeClient = sRetrofit.create(ApiInterface::class.java)

    suspend fun getHomeSeminar() = homeClient.getHomeSeminar()
    suspend fun getHomeNetworking() = homeClient.getHomeNetworking()
    suspend fun getHomeUser() = homeClient.getHomeUser()
    suspend fun getHomeProgram(memberIdx : Int) = homeClient.getHomeProgram(memberIdx)
    suspend fun getNotificationScroll(memberIdx : Int, lastNotificationIdx : Int) = homeClient.getNotification(memberIdx, lastNotificationIdx)
    suspend fun getNotification(memberIdx : Int) = homeClient.getNotification(memberIdx)
    suspend fun getNotificationUnread(memberIdx: Int) = homeClient.getNotificationUnread(memberIdx)
    suspend fun postLogin(request: LoginRequest) = homeClient.postLogin(request)

}