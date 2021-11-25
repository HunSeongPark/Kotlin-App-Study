package com.hunseong.hilt.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogDBRepository @Inject constructor(private val logDao: LogDao, private val ioDispatcher : CoroutineDispatcher) : LogRepository {

    override suspend fun add(msg: String) = withContext(ioDispatcher) {
        logDao.add(Log(msg = msg))
    }

    override suspend fun getLogs(): List<Log> = withContext(ioDispatcher) {
        logDao.getLogs()
    }

}