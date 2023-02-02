package com.softsquared.template.Garamgaebi.model

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication
import com.softsquared.template.Garamgaebi.src.main.seminar.data.SeminarDetailRequest
import retrofit2.http.Query

class SeminarRepository {
    private val seminarClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)
    //세미나 발표 리스트
    suspend fun getSeminarsInfo(seminaridx : Int) = seminarClient.getSeminarsInfo(seminaridx)
    //세미나 상세정보 조회
    suspend fun getSeminarDetail(memberIdx: Int,programIdx: Int)= seminarClient.getSeminarDetail(0,0)
    //세미나 신청자 리스트 조회
    suspend fun getSeminarParticipants(seminaridx : Int) = seminarClient.getSeminarParticipants(seminaridx)
}