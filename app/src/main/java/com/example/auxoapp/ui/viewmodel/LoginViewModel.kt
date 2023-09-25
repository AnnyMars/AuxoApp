package com.example.auxoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auxoapp.data.models.UserModel
import com.example.auxoapp.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: DataStoreRepository): ViewModel() {
    val userInfo: Flow<UserModel?> = repository.userInfoFlow

    fun saveUser(login: String, password: String) = viewModelScope.launch {
        repository.saveUser(login, password)
    }
}