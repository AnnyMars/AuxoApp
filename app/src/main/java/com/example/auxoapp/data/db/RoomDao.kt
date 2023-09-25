package com.example.auxoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.data.models.RoomInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(room: RoomInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingInfo)

    @Query("SELECT * FROM roomInfo")
    fun getAllRooms(): LiveData<List<RoomInfo>>

    @Query("SELECT * FROM roomInfo WHERE id = :id")
    suspend fun getRoomById(id: Int): RoomInfo

    @Query("SELECT * FROM bookingInfo WHERE roomId = :id")
    fun getBookingByRoomId(id: Int): LiveData<List<BookingInfo>>

}