package com.softsquared.template.Garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

//네트워킹 상세정보 조회
data class NetworkingInfoResponse(
    @SerializedName("result")val result : NetworkingInfoReult
):BaseResponse()

data class NetworkingInfoReult(
    @SerializedName("programIdx") val programIdx: Int,
    @SerializedName("title")val title: String,
    @SerializedName("date")val date: String,
    @SerializedName("location")val location: String,
    @SerializedName("fee")val fee: Int,
    @SerializedName("endDate")val endDate: String,
    @SerializedName("programStatus")val programStatus: String,
    @SerializedName("userButtonStatus")val userButtonStatus: String
)

//네트워킹 신청자 리스트 조회
data class NetworkingParticipantsResponse(
    @SerializedName("result")val result : List<NetworkingParticipantsResult>
)

data class NetworkingParticipantsResult(
    @SerializedName("memberIdx") val memberIdx : Int,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImg")val profileImg : String
)