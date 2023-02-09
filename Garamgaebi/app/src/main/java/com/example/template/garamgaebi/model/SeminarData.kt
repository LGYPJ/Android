package com.example.template.garamgaebi.src.main.seminar.data


import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse


//세미나 발표 리스트 조회
data class SeminarPresentResponse(
    val result : List<PresentationResult>
): BaseResponse()

data class PresentationResult(
    val presentationIdx : Int=-1,
    val title: String="",
    val nickname : String="",
    val profileImgUrl : String="",
    val organization : String="",
    val content : String="",
    val presentationUrl : String=""
)
//세미나 상세정보 조회
data class SeminarDetailInfoResponse(
    val isSuccess: Boolean = false,
    val code: Int = -1,
    val message: String ="",
    val result : SeminarDetailInfoResult
)


data class SeminarDetailInfoResult(
    val programIdx: Int=-1,
    val title: String="",
    var date: String="",
    val location: String="",
    var fee: Int=-1,
    var endDate: String="",
    val programStatus: String="",
    val userButtonStatus: String=""
)

data class SeminarDetailRequest (
    val memberIdx : Int,
   val programIdx: Int)

//세미나 신청자 리스트 조회
data class SeminarParticipantsResponse(
    val isSuccess: Boolean = false,
    val code: Int = 0,
    val message: String? = null,
    val result : List<SeminarParticipantsResult>
)

data class SeminarParticipantsResult (
    val memberIdx : Int,
    val nickname : String,
    val profileImg : String
)