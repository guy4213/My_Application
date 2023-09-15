package com.example.myapplication.data.mapper

import com.example.myapplication.data.entity.Player
import com.example.myapplication.service.dto.PlayerDto


//dto Mappers-design pattern
//dto mapping
//transferring PlayerDto to Player.

fun PlayerDto.toPlayer(): Player {
    return Player(
        birthday = birthday?: "null",
        birthLocation = birthLocation?: "null",
        englishDescription = englishDescription ?: "null",
        instagram = instagram?: "null",
        nation = nation?: "null",
        number = number?: "null",
        fullName = fullName ?: "null",
        position = position ?: "null",
        sportType = sportType ?: "null",
        team = team ?: "null",
        picture = picture?: "null"
    )
}