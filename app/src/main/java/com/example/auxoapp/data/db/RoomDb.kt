package com.example.auxoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.data.models.RoomInfo

@Database(entities = [RoomInfo::class, BookingInfo::class], version = 9)
abstract class RoomDb: RoomDatabase() {
    abstract fun getRoomDao(): RoomDao
}