package com.garamgaebi.garamgaebi.garamgaebi.model

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: NotificationResult
)
data class NotificationResult(
    val hasNext: Boolean,
    val result: List<NotificationList>
)
data class NotificationList(
    val content: String,
    var isRead: Boolean,
    val notificationIdx: Int,
    val notificationType: String,
    @SerializedName("resourceIdx") val programIdx: Int,
    val resourceType: String
)