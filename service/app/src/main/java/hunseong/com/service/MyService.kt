package hunseong.com.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    // bind가 필요 없을 경우 null return
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("serviceTest", "onStartCommand: ${Thread.currentThread().name}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        Log.e("serviceTest", "onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.e("serviceTest", "onDestroy")
        super.onDestroy()
    }
}