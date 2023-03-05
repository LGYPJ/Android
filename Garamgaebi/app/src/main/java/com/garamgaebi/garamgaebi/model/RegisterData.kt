package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class RegisterEmailVerifyResponse(
    val result: RegisterEmailVerifyResult
) : BaseResponse()
data class RegisterEmailVerifyResult(
    val message : String = ""
)
data class RegisterSendEmailResponse(
    val result: Boolean = false
) : BaseResponse()


data class RegisterSendEmailRequest(
    val email : String = ""
)

data class RegisterEmailVerifyRequest(
    val email : String = "",
    val key : String = ""
)
data class RegisterResponse(
    val result: RegisterResult
) : BaseResponse()

data class RegisterResult(
    val memberIdx: Int
)
data class RegisterRequest(
    val nickname : String,
    val profileEmail : String,
    val accessToken : String,
    val uniEmail : String,
    val status : String
)

