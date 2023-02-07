package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName

data class SeminarDetailInfoResult(
    @SerializedName("programIdx") val programIdx: Int,
    @SerializedName("title")val title: String,
    @SerializedName("date")val date: String,
    @SerializedName("location")val location: String,
    @SerializedName("fee") var fee: Int,
    @SerializedName("endDate")val endDate: String,
    @SerializedName("programStatus")val programStatus: String,
    @SerializedName("userButtonStatus")val userButtonStatus: String
)
