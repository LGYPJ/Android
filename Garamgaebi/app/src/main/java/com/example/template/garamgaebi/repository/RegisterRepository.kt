package com.example.template.garamgaebi.repository

import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.ApiInterface
import retrofit2.create

class RegisterRepository {
    private val registerClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun postEmailConfirm(email:String) = registerClient.postEmailConfirm(email)
}