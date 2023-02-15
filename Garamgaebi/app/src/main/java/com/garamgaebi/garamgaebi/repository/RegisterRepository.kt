package com.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.model.RegisterEmailVerifyRequest
import com.garamgaebi.garamgaebi.model.RegisterSendEmailRequest

class RegisterRepository {
    private val registerClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun postSendEmail(request: RegisterSendEmailRequest) = registerClient.postSendEmail(request)
    suspend fun postEmailVerify(request: RegisterEmailVerifyRequest) = registerClient.postEmailVerify(request)
}