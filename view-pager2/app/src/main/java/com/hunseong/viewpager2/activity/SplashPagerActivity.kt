package com.hunseong.viewpager2.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hunseong.viewpager2.adapter.SplashPagerAdapter
import com.hunseong.viewpager2.databinding.ActivitySplashPagerBinding

class SplashPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashPagerBinding

    private val splashAdapter: SplashPagerAdapter by lazy {
        SplashPagerAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        splashPager.adapter = splashAdapter
        TabLayoutMediator(indicator, splashPager) { _,_ -> }.attach()

        splashPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                if (position == 3) {
                    skipBtn.text = "OK"
                } else {
                    skipBtn.text = "SKIP"
                }
                super.onPageSelected(position)
            }
        })

        skipBtn.setOnClickListener {
            val pref = this@SplashPagerActivity.getSharedPreferences(MainActivity.PREFERENCES_NAME, Context.MODE_PRIVATE)

            pref.edit {
                putBoolean(MainActivity.IS_SPLASH_KEY, false)
            }

            val intent = Intent(this@SplashPagerActivity, DetailActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}