package com.garamgaebi.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.garamgaebi.model.CancelRequest
import com.garamgaebi.garamgaebi.garamgaebi.model.EnrollRequest

class ApplyRepository {
    private val applyClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    //신청취소
    suspend fun postCancel(cancelRequest : CancelRequest) = applyClient.postCancel(cancelRequest)
    //신청등록
    suspend fun postEnroll(enrollRequest : EnrollRequest) = applyClient.postEnroll(enrollRequest)
    //신청정보조회
    suspend fun getCancel(memberIdx:Int, programIdx:Int) = applyClient.getCancel(memberIdx,programIdx)

}