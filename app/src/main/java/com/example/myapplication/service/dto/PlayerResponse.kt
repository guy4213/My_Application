package com.example.myapplication.service.dto

import com.google.gson.annotations.SerializedName
//gets us the player dto list
data class PlayerResponse(
    @SerializedName("player")
    val players: List<PlayerDto>? = listOf()
)