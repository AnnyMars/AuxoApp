package com.example.auxoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.data.models.RoomInfo
import com.example.auxoapp.data.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val dbRepository: DbRepository): ViewModel() {
    private val _room = MutableLiveData<RoomInfo>()
    val room: MutableLiveData<RoomInfo> get() = _room


    fun getRoomById(id: Int) = viewModelScope.launch{
        _room.postValue(dbRepository.getRoomById(id))
    }
    fun insertBooking(booking: BookingInfo) = viewModelScope.launch(Dispatchers.IO){
        dbRepository.insertBooking(booking)
    }
    fun getBookingByRoomId(id: Int): LiveData<List<BookingInfo>> = dbRepository.getBookingByRoomId(id)

}