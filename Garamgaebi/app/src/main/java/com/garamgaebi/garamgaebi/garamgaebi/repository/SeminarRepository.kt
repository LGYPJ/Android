package com.garamgaebi.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.model.ApiInterface

class SeminarRepository {
    private val seminarClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)
    //세미나 발표 리스트
    suspend fun getSeminarsInfo(seminarIdx : Int) = seminarClient.getSeminarsInfo(seminarIdx)
    //세미나 상세정보 조회
    suspend fun getSeminarDetail(seminarIdx: Int, memberIdx: Int)= seminarClient.getSeminarDetail(seminarIdx,memberIdx)
    //세미나 신청자 리스트 조회
    suspend fun getSeminarParticipants(seminarIdx : Int, memberIdx: Int) = seminarClient.getSeminarParticipants(seminarIdx, memberIdx)
}