package com.hunseong.hilt.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
}