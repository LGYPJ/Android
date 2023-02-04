package com.example.template.garamgaebi.dto

import com.google.gson.annotations.SerializedName
import com.example.template.garamgaebi.config.BaseResponse

data class SnsDataResponse (
    @SerializedName("result")
    val result: ArrayList<SnsData>
) : BaseResponse()

data class SnsData (
    val snsIdx : Int = 0,
    val address : String
    )