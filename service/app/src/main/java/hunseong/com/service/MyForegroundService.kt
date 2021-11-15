package hunseong.com.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyForegroundService : Service() {

    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        registerNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_DOWNLOAD_ID, createDownloadingNotification(0))
        thread {
            Log.e("threadTest", "onStartCommand: ${Thread.currentThread().name}", )
            for (i in 1..100) {
                Thread.sleep(100)
                updateProgress(i)
            }
            stopForeground(true)
            stopSelf()
            notificationManager.notify(NOTIFICATION_COMPLETE_ID, createCompleteNotification())
        }
        return START_STICKY
    }

    private fun updateProgress(progress: Int) {
        notificationManager.notify(NOTIFICATION_DOWNLOAD_ID, createDownloadingNotification(progress))
    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(createNotificationChannel())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() =
        NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW).apply {
            description = CHANNEL_DESCRIPTION
            // Icon에 알림 갯수 badge show
            setShowBadge(true)
            // 잠금 화면에서 알림 표시 여부 및 공개 범위
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        }

    private fun createDownloadingNotification(progress: Int) =
        NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Download contents..")
            setContentText("Wait..")
            setSmallIcon(R.drawable.ic_launcher_background)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setContentIntent(
                PendingIntent.getActivity(
                    this@MyForegroundService, 0, Intent(this@MyForegroundService, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }, 0
                )
            )
            setProgress(100, progress, false)
        }.build()

    private fun createCompleteNotification() =
        NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Download complete!")
            setContentText("Awesome 🔥")
            setSmallIcon(R.drawable.ic_launcher_background)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setContentIntent(
                PendingIntent.getActivity(
                    this@MyForegroundService, 0, Intent(this@MyForegroundService, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }, 0
                )
            )
        }.build()

    companion object {
        private const val NOTIFICATION_DOWNLOAD_ID = 1
        private const val NOTIFICATION_COMPLETE_ID = 2
        private const val CHANNEL_ID = "my_channel"
        private const val CHANNEL_NAME = "channel_name"
        private const val CHANNEL_DESCRIPTION = "example notification channel description"
    }
}