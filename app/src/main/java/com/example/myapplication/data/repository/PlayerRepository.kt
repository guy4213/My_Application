package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.PlayerDao
import com.example.myapplication.data.entity.Player
import com.example.myapplication.data.mapper.toPlayer
import com.example.myapplication.service.PlayerService
import com.example.myapplication.service.dto.PlayerDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PlayerRepository @Inject constructor(private val playerdao: PlayerDao, private val playerService:PlayerService) {
///for the init call.
    suspend fun getPlayerList() = playerdao.getPlayersList()


    /**
     * fetches players by name from api
     * and updates database with results
     * @param name query to search
     * @return List<Player> - the player list
     */
    suspend fun refreshPlayers(name:String) : List<Player> {
         return withContext(Dispatchers.IO) {
           val playerResponse = playerService.getPlayers(name)
           val players = playerResponse.players?.map(PlayerDto::toPlayer) ?: listOf()
           playerdao.add(players)
           return@withContext players
       }
    }
}