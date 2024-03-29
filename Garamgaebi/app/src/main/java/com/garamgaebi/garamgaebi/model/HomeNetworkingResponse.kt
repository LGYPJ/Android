package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

data class HomeNetworkingResponse(
    val result: List<HomeNetworkingResult> = arrayListOf()
) : BaseResponse()
data class HomeNetworkingResult(
    val date: String = "",
    val isOpen: String = "",
    val location: String = "",
    val payment: String = "",
    val programIdx: Int = -1,
    val status: String = "",
    val title: String = "",
    val type: String = ""
)