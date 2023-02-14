package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.BaseResponse

data class RegisterEmailResponse(
    val result: RegisterEmailResult
) : BaseResponse()
data class RegisterEmailResult(
    val key: String
)