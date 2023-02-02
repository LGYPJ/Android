package com.softsquared.template.Garamgaebi.model

import com.softsquared.template.Garamgaebi.config.GaramgaebiApplication.Companion.sRetrofit

class GatheringRepository {
    private val gatheringClient = sRetrofit.create(ApiInterface::class.java)

    suspend fun getGatheringProgramReady(memberIdx : Int) = gatheringClient.getGatheringProgramReady(memberIdx)
    suspend fun getGatheringProgramClosed(memberIdx : Int) = gatheringClient.getGatheringProgramClosed(memberIdx)
}