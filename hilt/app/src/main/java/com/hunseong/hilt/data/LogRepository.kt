package com.hunseong.hilt.data

interface LogRepository {
    suspend fun add(msg: String)
    suspend fun getLogs() : List<Log>
}