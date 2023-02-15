package com.example.template.garamgaebi.model

data class MessageV0(
    val type : String,
    val roomId : String,
    val sender : String,
    val message : String,
    val profileUrl : String
)
