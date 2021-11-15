package com.hunseong.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import java.lang.StringBuilder

class GoAsyncBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val pendingResult: PendingResult = goAsync()
        val asyncTask = Task(pendingResult, intent)
        asyncTask.execute()
    }

    private class Task(
        private val pendingResult: PendingResult,
        private val intent: Intent
    ) : AsyncTask<String, Int, String>() {

        override fun doInBackground(vararg p0: String?): String {
            val sb = StringBuilder()
            sb.append("Action : ${intent.action} / ")
            sb.append("URI : ${intent.toUri(Intent.URI_INTENT_SCHEME)}")
            return sb.toString().also {
                Log.e("goAsyncLog", it)
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pendingResult.finish()
        }

    }
}