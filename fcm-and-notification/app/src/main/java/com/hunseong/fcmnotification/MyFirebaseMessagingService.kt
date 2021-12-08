package com.hunseong.fcmnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // 앱 제거 후 재설치 등 Token이 새로 발급되었을 때 동작
    // 서버 단에서 새로 Token 값을 저장하는 등의 동작 수행 할 수 있음
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    // FCM에서 메세지 수신 시 동작
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Notification Channel 생성
        createNotificationChannel()

        // RemoteMessage에서 값 추출
        val type = remoteMessage.data["type"]?.let {
            NotificationType.valueOf(it)
        } ?: return
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]


        // 알림 생성
        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, message))
    }

    private fun createNotificationChannel() {

        // Oreo 이상 버전에 대해 Notification Channel 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(
        type: NotificationType,
        title: String?,
        message: String?,
    ): Notification {

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title} 타입")

            // 중복된 Activity가 Top에 위치할 시 기존 Top 제거 후 새로운 Activity 생성
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        // Notification Manager 측에게 Intent 위임하기 위한 PendingIntent
        // FLAG_UPDATE_CURRENT : 같은 ID를 가진 알림에 대해서는 Update 수행
        val pendingIntent = PendingIntent.getActivity(this, type.id, intent, FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_check_circle_outline_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // 알림 클릭 시 알림 삭제

        when (type) {
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                // 확장형 알림 (장문 텍스트 형태)
                notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText("💰🎄\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0" +
                            "\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D" +
                            "\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84" +
                            "\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84\uD83D\uDCB0\uD83C\uDF84"))
            }
            NotificationType.CUSTOM -> Unit
        }
        return notificationBuilder.build()
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}