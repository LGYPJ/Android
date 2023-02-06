package com.example.template.garamgaebi.model

import com.google.gson.annotations.SerializedName

data class SeminarParticipantsResult(
    @SerializedName("memberIdx") val memberIdx : Int,
    @SerializedName("nickname")val nickname : String,
    @SerializedName("profileImg")val profileImg : String
)
