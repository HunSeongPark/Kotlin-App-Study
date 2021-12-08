package com.hunseong.fcmnotification

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.result_tv)
    }

    private val firebaseToken: TextView by lazy {
        findViewById(R.id.token_tv)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirebase()
        updateResult()
    }

    // 기존 Activity가 foreground인 상태에서 새로운 intent 들어올 시 onCreate 대신 onNewIntent 호출됨
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // PendingIntent를 통해 새로운 intent 들어올 시 setIntent를 통해 새로운 Intent로 교체
        setIntent(intent)
        updateResult(true)
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    firebaseToken.text = token
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent: Boolean = false) {
        resultTextView.text =
            (intent.getStringExtra("notificationType") ?: "앱 런처") + if (isNewIntent) {
                "(으)로 갱신했습니다."
            } else {
                "(으)로 실행했습니다."
            }
    }
}