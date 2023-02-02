package com.softsquared.template.Garamgaebi.model

data class GatheringProgramResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<GatheringProgramResult>
)

data class GatheringProgramResult(
    val date: String,
    val location: String,
    val payment: String,
    val programIdx: Int,
    val status: String,
    val title: String,
    val type: String
)