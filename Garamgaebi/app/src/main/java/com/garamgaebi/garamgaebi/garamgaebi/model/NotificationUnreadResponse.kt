package com.garamgaebi.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.garamgaebi.common.BaseResponse

data class NotificationUnreadResponse(
    val result: NotificationUnreadResult
) : BaseResponse()
data class NotificationUnreadResult(
    val isUnreadExist: Boolean = false
)