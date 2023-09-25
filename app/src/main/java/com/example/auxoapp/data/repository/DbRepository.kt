package com.example.auxoapp.data.repository

import com.example.auxoapp.data.db.RoomDb
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.data.models.RoomInfo
import javax.inject.Inject

class DbRepository @Inject constructor(private val db: RoomDb) {
    suspend fun insertRooms(room: RoomInfo) = db.getRoomDao().insertRooms(room)
    suspend fun insertBooking(booking: BookingInfo) = db.getRoomDao().insertBooking(booking)
    fun getAllRoom() = db.getRoomDao().getAllRooms()
    suspend fun getRoomById(roomId: Int) = db.getRoomDao().getRoomById(roomId)
    fun getBookingByRoomId(roomId: Int) = db.getRoomDao().getBookingByRoomId(roomId)
}