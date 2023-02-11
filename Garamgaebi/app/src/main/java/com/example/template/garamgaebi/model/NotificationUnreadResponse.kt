package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.BaseResponse

data class NotificationUnreadResponse(
    val result: NotificationUnreadResult
) : BaseResponse()
data class NotificationUnreadResult(
    val isUnreadExist: Boolean = false
)