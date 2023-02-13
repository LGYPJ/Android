package com.example.template.garamgaebi.model

import com.example.template.garamgaebi.common.BaseResponse

//member post
data class GameMemberPostRequest(
    val roomId : String,
    val memberIdx : Int
)

data class GameMemberPostResponse(
    val result : GameMemberPostResult
):BaseResponse()

data class GameMemberPostResult(
    val message : String
)

//member delete
data class GameMemberDeleteRequest(
    val roomId : String,
    val memberIdx : Int
)

data class GameMemberDeleteResponse(
    val result : GameMemberDeleteResult
)

data class GameMemberDeleteResult(
    val message: String
)

//room
data class GameRoomResponse(
    val result :GameRoomResult
):BaseResponse()

data class GameRoomResult(
    val programGameRoomIdx : Int,
    val programIdx : Int,
    val roomId : String
)

//images
data class GameImagesResponse(
    val result : List<String>
):BaseResponse()


//member get
data class GameMemberGetRequest(
    val roomId : String
)

data class GameMemberGetResponse(
    val result : GameMemberGetResult
):BaseResponse()

data class GameMemberGetResult(
    val memberIdx: Int,
    val nickname : String,
    val profileUrl : String
)