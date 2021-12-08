package com.hunseong.workmanager.worker

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params : WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val number = inputData.getInt(EXTRA_NUMBER, 0)
        val result = number * number
        SystemClock.sleep(5000)
        Log.d("SimpleWorker", "SimpleWorker finished: $result")

        val outputData = Data.Builder()
            .putInt(EXTRA_RESULT, result)
            .build()

        // 결과를 outputData로 전달
        return Result.success(outputData)
    }

    companion object {
        const val EXTRA_NUMBER = "extra_number"
        const val EXTRA_RESULT = "extra_result"
        const val TAG = "simple_worker"
    }
}