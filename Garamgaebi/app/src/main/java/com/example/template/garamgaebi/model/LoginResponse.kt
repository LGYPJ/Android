package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.config.BaseResponse
import com.google.gson.annotations.SerializedName

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
    var uniEmail : String,
    var password : String
)