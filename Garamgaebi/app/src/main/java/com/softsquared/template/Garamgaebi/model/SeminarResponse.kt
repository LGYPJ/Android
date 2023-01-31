package com.softsquared.template.Garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

data class SeminarPresentResponse(
    @SerializedName("result") val result : List<PresentationResult>
):BaseResponse()

data class PresentationResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImgUrl")val profileImgUrl : String,
    @SerializedName("organization") val organization : String,
    @SerializedName("content") val content : String,
    @SerializedName("presentationUrl") val presentationUrl : String
)