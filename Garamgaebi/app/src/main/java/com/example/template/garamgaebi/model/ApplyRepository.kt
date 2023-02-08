package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.GaramgaebiApplication

class ApplyRepository {
    private val applyClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    //신청취소
    suspend fun postCancel(cancelRequest : CancelRequest) = applyClient.postCancel(cancelRequest)
    //신청등록
    suspend fun postEnroll(enrollRequest : EnrollRequest) = applyClient.postEnroll(enrollRequest)

}