package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.PlayerDao
import com.example.myapplication.data.entity.Player

const val DB_VERSION = 1
const val DB_NAME = "AppDatabase"
//database that gets its data with the help of entity - Player
@Database(entities = [Player::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    //method for getting the db
    abstract fun playerDao(): PlayerDao
    //creating the db,and recreating it to older version if the new one has not been found
    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
//CREATE DATABASE AppDatabase;