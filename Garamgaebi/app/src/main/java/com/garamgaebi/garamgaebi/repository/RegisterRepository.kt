package com.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.model.ApiInterface

class RegisterRepository {
    private val registerClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun postEmailConfirm(email:String) = registerClient.postEmailConfirm(email)
}