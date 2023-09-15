package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entity.Player

@Dao
// data access obj- getting the  data with query requests after transferring it from playerDto to player
interface PlayerDao {
    //REPLACE = update db from cloud
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(players:List<Player>)
//dynamic list that is being updated by the recycler view
    @Query("SELECT * FROM Player")
    fun getPlayers(): LiveData<List<Player>>
//for the the init call.
    @Query("SELECT * FROM Player")
    suspend fun getPlayersList(): List<Player>
}
