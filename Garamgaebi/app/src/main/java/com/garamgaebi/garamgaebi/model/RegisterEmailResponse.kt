package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class RegisterEmailResponse(
    val result: RegisterEmailResult
) : BaseResponse()
data class RegisterEmailResult(
    val key: String
)