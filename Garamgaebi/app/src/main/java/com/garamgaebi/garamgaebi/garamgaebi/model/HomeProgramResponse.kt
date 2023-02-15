package com.garamgaebi.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.garamgaebi.common.BaseResponse

data class HomeProgramResponse(
    val result: List<HomeProgramResult> = arrayListOf()
): BaseResponse()
data class HomeProgramResult(
    val date: String = "",
    val isOpen: String = "",
    val location: String = "",
    val payment: String = "",
    val programIdx: Int = -1,
    val status: String = "",
    val title: String = "",
    val type: String = ""
)