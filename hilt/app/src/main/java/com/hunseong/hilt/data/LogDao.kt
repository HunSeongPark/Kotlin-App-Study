package com.hunseong.hilt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDao {
    @Query("SELECT * FROM Log")
    suspend fun getLogs() : List<Log>

    @Insert
    suspend fun add(log: Log)
}