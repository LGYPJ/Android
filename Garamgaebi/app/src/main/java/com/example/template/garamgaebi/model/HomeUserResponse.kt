package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.common.BaseResponse

data class HomeUserResponse(
    val result: List<HomeUserResult>
) : BaseResponse()
data class HomeUserResult(
    val belong: String = "",
    val memberIdx: Int = -1,
    val nickName: String = "",
    val profileUrl: String = "",
    val group: String = "",
    val detail: String = "",
)