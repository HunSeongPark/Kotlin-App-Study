package com.hunseong.hilt.di

import android.content.Context
import androidx.room.Room
import com.hunseong.hilt.data.AppDatabase
import com.hunseong.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "logdata.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database: AppDatabase) : LogDao {
        return database.logDao()
    }
}