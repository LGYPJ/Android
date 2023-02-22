package com.garamgaebi.garamgaebi.model

data class MessageV0(
    val type : String,
    val roomId : String,
    val sender : String,
    val message : String,
    val profileUrl : String
)

data class Message(
    val type : String,
    val roomId : String,
    val sender : String,
    val message : String,
    val profileUrl : String
)
