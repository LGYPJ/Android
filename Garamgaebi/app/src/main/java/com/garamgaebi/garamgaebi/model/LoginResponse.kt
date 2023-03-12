package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class LoginResponse(
    val result: LoginResult
) : BaseResponse()


data class LoginResult(
    val tokenInfo : TokenInfoResult,
    val uniEmail : String,
    val nickname : String
)

data class TokenInfoResult(
    val accessToken: String,
    val grantType: String,
    val memberIdx: Int,
    val refreshToken: String,
    val refreshTokenExpirationTime: Long
)

data class LoginRequest(
    val accessToken : String,
    val fcmToken: String
)

data class AutoLoginRequest (
    val refreshToken : String
)