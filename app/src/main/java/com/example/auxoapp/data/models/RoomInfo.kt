package com.example.auxoapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roomInfo")
data class RoomInfo(
    @PrimaryKey val id: Int,
    val name: String,
    val place: String,
    @ColumnInfo(defaultValue = "Пусто")
    val time: String = "Пусто",
    val icon1: Boolean,
    val icon2: Boolean
)
