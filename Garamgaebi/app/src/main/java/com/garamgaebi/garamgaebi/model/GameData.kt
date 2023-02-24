package com.garamgaebi.garamgaebi.model

import com.garamgaebi.garamgaebi.common.BaseResponse

//member post
data class GameMemberPostRequest(
    val roomId : String
)

data class GameMemberPostResponse(
    val result : GameMemberPostResult
):BaseResponse()

data class GameMemberPostResult(
    val message : String,
    val currentImgIdx : Int,
    val currentMemberIdx: Int
)

//member delete
data class GameMemberDeleteRequest(
    val roomId : String,
    val nextMemberIdx : Int
)

data class GameMemberDeleteResponse(
    val result : String
):BaseResponse()

/*data class GameMemberDeleteResult(
    val result : String
):BaseResponse()*/

//room
data class GameRoomResponse(
    val result :List<GameRoomResult>
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
    val result : List<GameMemberGetResult>
):BaseResponse()

data class GameMemberGetResult(
    val memberIdx: Int,
    val nickname : String,
    val profileUrl : String
)

/*data class GameMemberGetResultRe(
    val result : GameMemberGetResult,
    val next : Boolean = false
)*/


// /game/current-idx
data class GameCurrentIdxResponse(
    val result : String
):BaseResponse()

data class GameCurrentIdxRequest(
    val roomId: String,
    val nextMemberIdx : Int
)