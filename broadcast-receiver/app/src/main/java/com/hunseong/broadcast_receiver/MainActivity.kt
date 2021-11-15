package com.hunseong.broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val receiver = MyBroadcastReceiver()
    private val goAsyncReceiver = GoAsyncBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receiverFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val goAsyncFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED)

        registerReceiver(receiver, receiverFilter)
        registerReceiver(goAsyncReceiver, goAsyncFilter)

//        ** To register for local broadcasts **
//        LocalBroadcastManager.getInstance(this).registerReceiver(br, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unregisterReceiver(goAsyncReceiver)
    }
}