package com.softsquared.template.Garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

//신청 취소
data class CancelRequest(
    @SerializedName("memberIdx")val memberIdx: Int,
    @SerializedName("programIdx")val programIdx : Int,
    @SerializedName("bank")val bank : String,
    @SerializedName("account")val account : String
)

data class CancelResponse(
    @SerializedName("result")val result : Int
):BaseResponse()

//신청 등록
data class EnrollRequest(
    @SerializedName("memberIdx")val memberIdx: Int,
    @SerializedName("programIdx")val programIdx : Int,
    @SerializedName("name")val name : String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("phone")val phone : String
)

data class EnrollResponse(
    @SerializedName("result")val result : Int
):BaseResponse()
