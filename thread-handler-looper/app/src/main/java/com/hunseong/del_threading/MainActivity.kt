package com.hunseong.del_threading

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var handler: Handler

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.tv)

        // Main Thread Handler
        handler = Handler()

        val timerThread = TimerThread()
        timerThread.start()

        findViewById<Button>(R.id.stop_btn).setOnClickListener {
            timerThread.stopRunning()
        }
    }

    inner class TimerThread : Thread() {

        private var time = 0
        private var isRunning = true

        override fun run() {
            super.run()
            while (isRunning) {

                //1초 Delay
                sleep(1000)

                // Main Thread Handler에 post를 통해 UI 작업 처리
                handler.post {
                    title.text = "Worker Thread 시작 후 ${time}초"
                    time++
                }
            }

            // isRunning = false 이후 UI 작업 처리 (Thread 종료 전)
            handler.post {
                title.text = "Worker Thread가 종료되었습니다."
            }
        }

        fun stopRunning() {
            isRunning = false
        }
    }
}