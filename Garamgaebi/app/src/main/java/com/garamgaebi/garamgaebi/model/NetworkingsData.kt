package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

//네트워킹 상세정보 조회
data class NetworkingInfoResponse(
   val result : NetworkingInfoReult
):BaseResponse()

data class NetworkingInfoReult(
    val programIdx: Int =-1,
    val title: String="",
    var date: String="",
    val location: String="",
    val fee: Int=-1,
    var endDate: String="",
    val programStatus: String="",
    val userButtonStatus: String=""
)

//네트워킹 신청자 리스트 조회
data class NetworkingParticipantsResponse(
    val result : NetworkingParticipantsResult
):BaseResponse()

data class NetworkingParticipantsResult(
    val participantList: List<NetworkingResult>,
    val isApply : Boolean
)

data class NetworkingResult(
    val memberIdx: Int=-1,
    val nickname: String = "",
    val profileImg: String? = null
)
