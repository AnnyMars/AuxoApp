package com.example.auxoapp.data.repository


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.auxoapp.data.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.log

class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    val userInfoFlow: Flow<UserModel?> = dataStore.data
        .map { pref ->
            val login = pref[PrefKey.LOGIN]
            val password = pref[PrefKey.PASSWORD]
            if (login != null && password != null) UserModel(login, password) else null
        }

    suspend fun saveUser(login: String, password: String){
        dataStore.edit {
            it[PrefKey.LOGIN] = login
            it[PrefKey.PASSWORD] = password
        }
    }


    private object PrefKey{
        val LOGIN = stringPreferencesKey("login")
        val PASSWORD = stringPreferencesKey("password")
    }
}