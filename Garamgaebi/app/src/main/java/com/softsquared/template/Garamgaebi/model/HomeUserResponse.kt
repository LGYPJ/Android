package com.softsquared.template.Garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

data class HomeUserResponse(
    @SerializedName("result")val result: List<HomeUserResult>
) : BaseResponse()
data class HomeUserResult(
    @SerializedName("belong")val belong: String,
    @SerializedName("memberIdx")val memberIdx: Int,
    @SerializedName("nickName")val nickName: String,
    @SerializedName("profileUrl")val profileUrl: String
)