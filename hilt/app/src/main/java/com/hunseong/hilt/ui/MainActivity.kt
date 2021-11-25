package com.hunseong.hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hunseong.hilt.R
import com.hunseong.hilt.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.navigate(LogFragment())
    }
}