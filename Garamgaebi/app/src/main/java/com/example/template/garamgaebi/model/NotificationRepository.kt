package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.config.GaramgaebiApplication

class NotificationRepository {
    private val notificationClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun getNotification(memberIdx : Int) = notificationClient.getNotification(memberIdx)
}