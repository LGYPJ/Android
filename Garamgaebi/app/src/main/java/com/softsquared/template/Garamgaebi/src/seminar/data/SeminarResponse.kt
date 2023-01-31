package com.softsquared.template.Garamgaebi.src.seminar.data

import com.google.gson.annotations.SerializedName
import com.softsquared.template.Garamgaebi.config.BaseResponse

data class SeminarPresentResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result : List<PresentationResult>
)

data class PresentationResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImgUrl")val profileImgUrl : String,
    @SerializedName("organization") val organization : String,
    @SerializedName("content") val content : String,
    @SerializedName("presentationUrl") val presentationUrl : String
)