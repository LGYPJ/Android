package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse

//신청 취소
data class CancelRequest(
    @SerializedName("memberIdx")val memberIdx: Int,
    @SerializedName("programIdx")val programIdx : Int,
    @SerializedName("bank")val bank : String,
    @SerializedName("account")val account : String
)

data class CancelResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null,
    @SerializedName("result")val result : Int
)

//신청 등록
data class EnrollRequest(
    @SerializedName("memberIdx") val memberIdx: Int,
    @SerializedName("programIdx") val programIdx: Int,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("phone") val phone: String
)

data class EnrollResponse(
    @SerializedName("result")val result : Int
):BaseResponse()
