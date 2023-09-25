package com.example.auxoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auxoapp.data.models.RoomInfo
import com.example.auxoapp.data.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DbRepository): ViewModel() {
    fun insertRooms(room:RoomInfo) =
        viewModelScope.launch(Dispatchers.IO){
            repository.insertRooms(room)
        }
    fun getAllRooms(): LiveData<List<RoomInfo>> = repository.getAllRoom()
}