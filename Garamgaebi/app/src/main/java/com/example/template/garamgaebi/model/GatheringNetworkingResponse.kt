package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.BaseResponse

data class GatheringNetworkingResponse(
    val result: GatheringNetworkingResult
) : BaseResponse()
data class GatheringNetworkingResult(
    val date: String = "",
    val isOpen: String = "",
    val location: String = "",
    val payment: String = "",
    val programIdx: Int = -1,
    val status: String = "",
    val title: String = "",
    val type: String = ""
)