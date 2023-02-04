package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.config.BaseResponse

data class GatheringSeminarClosedResponse(
    @SerializedName("result")val result: List<GatheringSeminarClosedResult>
) : BaseResponse()
data class GatheringSeminarClosedResult(
    @SerializedName("date")val date: String,
    @SerializedName("isOpen")val isOpen: String,
    @SerializedName("location")val location: String,
    @SerializedName("payment")val payment: String,
    @SerializedName("programIdx")val programIdx: Int,
    @SerializedName("status")val status: String,
    @SerializedName("title")val title: String,
    @SerializedName("type")val type: String
)