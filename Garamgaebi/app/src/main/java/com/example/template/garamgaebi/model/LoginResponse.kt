package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.config.BaseResponse

data class LoginResponse(
    val result: LoginResult
):BaseResponse()

data class LoginResult(
    val accessToken: String,
    val grantType: String,
    val refreshToken: String,
    val refreshTokenExpirationTime: Int
)

data class LoginRequest(
    var uniEmail : String,
    var password : String
)