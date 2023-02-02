package com.softsquared.template.Garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

data class GatheringNetworkingClosedResponse(
    @SerializedName("result")val result: List<GatheringNetworkingClosedResult>
) : BaseResponse()
data class GatheringNetworkingClosedResult(
    @SerializedName("date")val date: String,
    @SerializedName("isOpen")val isOpen: String,
    @SerializedName("location")val location: String,
    @SerializedName("payment")val payment: String,
    @SerializedName("programIdx")val programIdx: Int,
    @SerializedName("status")val status: String,
    @SerializedName("title")val title: String,
    @SerializedName("type")val type: String
)