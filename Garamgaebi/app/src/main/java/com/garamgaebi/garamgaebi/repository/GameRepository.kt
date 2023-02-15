package com.garamgaebi.garamgaebi.repository

import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.model.GameMemberDeleteRequest
import com.garamgaebi.garamgaebi.model.GameMemberGetRequest
import com.garamgaebi.garamgaebi.model.GameMemberPostRequest

class GameRepository {

    private val gameClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun postGameMember(gameMemberPostRequest: GameMemberPostRequest) = gameClient.postGameMember(gameMemberPostRequest)
    suspend fun deleteGameMember(gameMemberDeleteRequest: GameMemberDeleteRequest) = gameClient.deleteGameMember(gameMemberDeleteRequest)
    suspend fun getGameRoom(programIdx : Int) = gameClient.getGameRoom(programIdx)
    suspend fun getGameImage(programIdx: Int) = gameClient.getGameImage(programIdx)
    suspend fun getGameMember(gameMemberGetRequest: GameMemberGetRequest) = gameClient.getGameMember(gameMemberGetRequest)
}