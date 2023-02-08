package com.example.template.garamgaebi.repository

import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.ApiInterface

class NotificationRepository {
    private val notificationClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun getNotification(memberIdx : Int) = notificationClient.getNotification(memberIdx)
}