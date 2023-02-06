package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.config.GaramgaebiApplication

class NetworkingRepository {
    private val networkingClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    //네트워킹 상세정보 조회
    suspend fun getNetworkingInfo(networkingIdx: Int, memberIdx: Int) = networkingClient.getNetworkingInfo(networkingIdx,memberIdx)
    //네트워킹 신청자 리스트 조회
    suspend fun getNetworkingParticipants(networkingIdx : Int, memberIdx: Int) = networkingClient.getNetworkingParticipants(networkingIdx,memberIdx)
}