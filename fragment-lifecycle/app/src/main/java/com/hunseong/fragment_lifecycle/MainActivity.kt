package com.hunseong.fragment_lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FragmentLog", "MainActivity - onCreate", )
        Log.e("FragmentLog", "replace - FirstFragment")
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment())
//            .add(R.id.fragment_container, FirstFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Log.e("FragmentLog", "MainActivity - onStart", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("FragmentLog", "MainActivity - onResume", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("FragmentLog", "MainActivity - onPause", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("FragmentLog", "MainActivity - onStop", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FragmentLog", "MainActivity - onDestroy", )
    }

    fun navigateTo(fragment: Fragment) {
        Log.e("FragmentLog", "replace - ${fragment.javaClass.simpleName}")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
//            .add(R.id.fragment_container, fragment)
//            .addToBackStack(null)
            .commit()
    }
}