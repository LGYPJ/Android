package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class LoginResponse(
    val result: LoginResult
) : BaseResponse()
data class LoginResult(
    val accessToken: String,
    val grantType: String,
    val memberIdx: Int,
    val refreshToken: String,
    val refreshTokenExpirationTime: Int
)

data class LoginRequest(
    val accessToken : String,
    val fcmToken: String
)