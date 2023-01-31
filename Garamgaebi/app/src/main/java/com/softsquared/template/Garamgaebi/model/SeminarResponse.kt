package com.softsquared.template.Garamgaebi.src.main.seminar.data

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse
//세미나 발표 리스트 조회
data class SeminarPresentResponse(
    @SerializedName("result") val result : List<PresentationResult>
): BaseResponse()

data class PresentationResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImgUrl")val profileImgUrl : String,
    @SerializedName("organization") val organization : String,
    @SerializedName("content") val content : String,
    @SerializedName("presentationUrl") val presentationUrl : String
)
//세미나 상세정보 조회
data class SeminarDetailInfoResponse(
    @SerializedName("result")val result : SeminarDetailInfoResult
): BaseResponse()


data class SeminarDetailInfoResult(
    @SerializedName("programIdx") val programIdx: Int,
    @SerializedName("title")val title: String,
    @SerializedName("date")val date: String,
    @SerializedName("location")val location: String,
    @SerializedName("fee")val fee: Int,
    @SerializedName("endDate")val endDate: String,
    @SerializedName("programStatus")val programStatus: String,
    @SerializedName("userButtonStatus")val userButtonStatus: String
)

//세미나 신청자 리스트 조회
data class SeminarParticipantsResponse(
    @SerializedName("result")val result : List<SeminarParticipantsResult>
): BaseResponse()

data class SeminarParticipantsResult (
    @SerializedName("memberIdx") val memberIdx : Int,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImg")val profileImg : String
)