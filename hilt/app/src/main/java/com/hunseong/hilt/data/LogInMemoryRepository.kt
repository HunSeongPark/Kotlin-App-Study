package com.hunseong.hilt.data

import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class LogInMemoryRepository : LogRepository {

    private val logs = ArrayList<Log>()

    override suspend fun add(msg: String) {
        logs.add(Log(msg = msg))
    }

    override suspend fun getLogs(): List<Log> = logs
}