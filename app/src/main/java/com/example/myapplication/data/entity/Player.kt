package com.example.myapplication.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
//entity for db
@Entity
@Parcelize
class Player (
    val birthday: String,
    val birthLocation: String,
    val englishDescription: String,
    val instagram: String,
    val nation: String,
    val number: String,
    @PrimaryKey
    val fullName: String,
    val position: String,
    val sportType: String,
    val team: String,
    val picture: String):Parcelable
