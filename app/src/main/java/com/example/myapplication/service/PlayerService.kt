package com.example.myapplication.service

import com.example.myapplication.service.dto.PlayerResponse
import retrofit2.http.GET
import retrofit2.http.Query


//gets the api list w the extra string to the base url in get request.
// the method gets a parameter p to search the relevant player by its name.
//if the parameter has no value, will display the whole list.
interface PlayerService {
    @GET("searchplayers.php")
    suspend fun getPlayers(@Query("p") p: String?): PlayerResponse
}
//searching cristiano ronaldo:
//https://www.thesportsdb.com/api/v1/json/3/searchplayers.php?p=Cristiano%20Ronaldo
//all players:
//https://www.thesportsdb.com/api/v1/json/3/searchplayers.php?p