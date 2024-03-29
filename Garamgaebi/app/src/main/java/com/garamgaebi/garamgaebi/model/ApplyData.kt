package com.garamgaebi.garamgaebi.model


import com.garamgaebi.garamgaebi.common.BaseResponse

//신청 취소
data class CancelRequest(
    val memberIdx: Int = -1,
    val programIdx : Int =-1,
    val bank : String="",
   val account : String=""
)

data class CancelResponse(
    val result : Int=-1
):BaseResponse()

//신청 등록
data class EnrollRequest(
    val memberIdx: Int = -1,
    val programIdx: Int=-1,
    val name: String="",
    val nickname: String="",
    val phone: String=""
)

data class EnrollResponse(
    val result : Int=-1
):BaseResponse()

data class CancelInfoResponse(
    val result : CancelInfoResult
):BaseResponse()

data class CancelInfoResult(
    val name: String ="",
    val nickname: String="",
    val phone : String=""
)
