package com.hunseong.hilt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Log(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val msg: String
)