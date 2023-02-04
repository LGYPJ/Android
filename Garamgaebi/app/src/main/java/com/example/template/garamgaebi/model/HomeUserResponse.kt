package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.config.BaseResponse

data class HomeUserResponse(
    @SerializedName("result")val result: List<HomeUserResult>
) : BaseResponse()
data class HomeUserResult(
    @SerializedName("belong")val belong: String,
    @SerializedName("memberIdx")val memberIdx: Int,
    @SerializedName("nickName")val nickName: String,
    @SerializedName("profileUrl")val profileUrl: String,
    @SerializedName("group")val group: String,
    @SerializedName("detail")val detail: String,
)