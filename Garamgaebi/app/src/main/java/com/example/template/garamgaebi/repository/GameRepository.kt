package com.example.template.garamgaebi.repository

import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.model.ApiInterface
import com.example.template.garamgaebi.model.GameMemberDeleteRequest
import com.example.template.garamgaebi.model.GameMemberGetRequest
import com.example.template.garamgaebi.model.GameMemberPostRequest
import retrofit2.create

class GameRepository {

    private val gameClient = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)

    suspend fun postGameMember(gameMemberPostRequest: GameMemberPostRequest) = gameClient.postGameMember(gameMemberPostRequest)
    suspend fun deleteGameMember(gameMemberDeleteRequest: GameMemberDeleteRequest) = gameClient.deleteGameMember(gameMemberDeleteRequest)
    suspend fun getGameRoom(programIdx : Int) = gameClient.getGameRoom(programIdx)
    suspend fun getGameImage(programIdx: Int) = gameClient.getGameImage(programIdx)
    suspend fun getGameMember(gameMemberGetRequest: GameMemberGetRequest) = gameClient.getGameMember(gameMemberGetRequest)
}