package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class RegisterEmailResponse(
    val result: RegisterEmailResult
) : BaseResponse()
data class RegisterEmailResult(
    val message : String
)

data class RegisterSendEmailRequest(
    val email : String
)

data class RegisterEmailVerifyRequest(
    val email : String,
    val key : String
)