package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("result")val result: List<NotificationResult>
) : BaseResponse()
data class NotificationResult(
    @SerializedName("content")val content: String,
    @SerializedName("isRead")val isRead: Boolean,
    @SerializedName("notificationIdx")val notificationIdx: Int,
    @SerializedName("notificationType")val notificationType: String,
    @SerializedName("resourceIdx")val resourceIdx: Int,
    @SerializedName("resourceType")val resourceType: String
)