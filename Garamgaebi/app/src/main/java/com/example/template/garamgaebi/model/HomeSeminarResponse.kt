package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.config.BaseResponse

data class HomeSeminarResponse(
    @SerializedName("result") val result : List<HomeSeminarResult>
): BaseResponse()

data class HomeSeminarResult(
    @SerializedName("presentationIdx") val presentationIdx : Int,
    @SerializedName("title") val title: String,
    @SerializedName("date")val date : String,
    @SerializedName("location")val location : String,
    @SerializedName("type") val type : String,
    @SerializedName("payment") val payment : String,
    @SerializedName("status") val status : String,
    @SerializedName("isOpen") val isOpen : String

)
