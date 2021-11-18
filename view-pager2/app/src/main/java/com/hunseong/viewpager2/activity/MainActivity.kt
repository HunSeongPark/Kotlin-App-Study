package com.hunseong.viewpager2.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hunseong.viewpager2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkSplash()
    }

    private fun checkSplash() {
        val pref: SharedPreferences = this.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        val isSplash = pref.getBoolean(IS_SPLASH_KEY, true)

        if (isSplash) {
            val intent = Intent(this, SplashPagerActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        finish()
    }

    companion object {
        const val PREFERENCES_NAME = "shared_pref"
        const val IS_SPLASH_KEY = "is_splash_key"
    }
}