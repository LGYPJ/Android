package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class NotificationUnreadResponse(
    val result: NotificationUnreadResult
) : BaseResponse()
data class NotificationUnreadResult(
    val isUnreadExist: Boolean = false
)