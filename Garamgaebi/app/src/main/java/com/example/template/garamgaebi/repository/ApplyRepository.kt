package com.example.template.garamgaebi.repository

import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.ApiInterface
import com.example.template.garamgaebi.model.CancelRequest
import com.example.template.garamgaebi.model.EnrollRequest

class ApplyRepository {
    private val applyClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    //신청취소
    suspend fun postCancel(cancelRequest : CancelRequest) = applyClient.postCancel(cancelRequest)
    //신청등록
    suspend fun postEnroll(enrollRequest : EnrollRequest) = applyClient.postEnroll(enrollRequest)

}