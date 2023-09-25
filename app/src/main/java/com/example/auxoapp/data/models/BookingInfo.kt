package com.example.auxoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookingInfo")
data class BookingInfo(
    @PrimaryKey val id: Int,
    val roomId: Int,
    val date: String,
    val time: String,
    val name: String
)
