package com.example.template.garamgaebi.model


import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse

data class GatheringProgramResponse(
    @SerializedName("result")val result: List<GatheringProgramResult>
): BaseResponse()
data class GatheringProgramResult(
    @SerializedName("date")val date: String,
    @SerializedName("isOpen")val isOpen: String,
    @SerializedName("location")val location: String,
    @SerializedName("payment")val payment: String,
    @SerializedName("programIdx")val programIdx: Int,
    @SerializedName("status")val status: String,
    @SerializedName("title")val title: String,
    @SerializedName("type")val type: String
)