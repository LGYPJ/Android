package com.garamgaebi.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.garamgaebi.common.BaseResponse

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