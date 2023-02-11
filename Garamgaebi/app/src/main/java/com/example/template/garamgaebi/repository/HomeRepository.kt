package com.example.template.garamgaebi.repository

import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.sRetrofit
import com.example.template.garamgaebi.model.ApiInterface

class HomeRepository {
    private val homeClient = sRetrofit.create(ApiInterface::class.java)

    suspend fun getHomeSeminar() = homeClient.getHomeSeminar()
    suspend fun getHomeNetworking() = homeClient.getHomeNetworking()
    suspend fun getHomeUser() = homeClient.getHomeUser()
    suspend fun getHomeProgram(memberIdx : Int) = homeClient.getHomeProgram(memberIdx)
    suspend fun getNotification(memberIdx : Int, lastNotificationIdx : Int) = homeClient.getNotification(memberIdx, lastNotificationIdx)
    suspend fun getNotification(memberIdx : Int) = homeClient.getNotification(memberIdx)
    suspend fun getNotificationUnread(memberIdx: Int) = homeClient.getNotificationUnread(memberIdx)

}