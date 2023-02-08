package com.example.template.garamgaebi.src.main.seminar.data

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse
import com.example.template.garamgaebi.model.PresentationResult
import com.example.template.garamgaebi.model.SeminarDetailInfoResult
import com.example.template.garamgaebi.model.SeminarParticipantsResult

//세미나 발표 리스트 조회
data class SeminarPresentResponse(
    @SerializedName("result") val result : List<PresentationResult>
): BaseResponse()

/*data class PresentationResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImgUrl")val profileImgUrl : String,
    @SerializedName("organization") val organization : String,
    @SerializedName("content") val content : String,
    @SerializedName("presentationUrl") val presentationUrl : String
)*/
//세미나 상세정보 조회
data class SeminarDetailInfoResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result")val result : SeminarDetailInfoResult
)


/*data class SeminarDetailInfoResult(
    @SerializedName("programIdx") val programIdx: Int,
    @SerializedName("title")val title: String,
    @SerializedName("date")val date: String,
    @SerializedName("location")val location: String,
    @SerializedName("fee")val fee: Int,
    @SerializedName("endDate")val endDate: String,
    @SerializedName("programStatus")val programStatus: String,
    @SerializedName("userButtonStatus")val userButtonStatus: String
)*/

data class SeminarDetailRequest (
    @SerializedName("memberIdx") val memberIdx : Int,
    @SerializedName("programIdx") val programIdx: Int)

//세미나 신청자 리스트 조회
data class SeminarParticipantsResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result")val result : List<SeminarParticipantsResult>
)

/*data class SeminarParticipantsResult (
    @SerializedName("memberIdx") val memberIdx : Int,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImg")val profileImg : String
)*/