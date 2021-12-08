package com.hunseong.workmanager.worker

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hunseong.workmanager.worker.SimpleWorker.Companion.EXTRA_RESULT

class SimpleSecondWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val number = inputData.getInt(EXTRA_RESULT, 0)
        val result = number * 2
        SystemClock.sleep(5000)
        Log.d("SimpleSecondWorker", "SimpleSecondWorker finished: $result")

        val outputData = Data.Builder()
            .putInt(EXTRA_RESULT, result)
            .build()

        return Result.success(outputData)
    }
}