package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse

data class GatheringNetworkingClosedResponse(
    val result: List<GatheringNetworkingClosedResult>
) : BaseResponse()
data class GatheringNetworkingClosedResult(
    val date: String = "",
    val isOpen: String = "",
    val location: String = "",
    val payment: String = "",
    val programIdx: Int = -1,
    val status: String = "",
    val title: String = "",
    val type: String = ""
)