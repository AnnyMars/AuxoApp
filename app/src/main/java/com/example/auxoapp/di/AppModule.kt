package com.example.auxoapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.auxoapp.data.db.RoomDao
import com.example.auxoapp.data.db.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext appContext: Context): RoomDb{
        return Room.databaseBuilder(
            appContext,
            RoomDb::class.java,
            "roomDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: RoomDb): RoomDao{
        return db.getRoomDao()
    }

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {appContext.preferencesDataStoreFile("userDataStore")}
        )
}