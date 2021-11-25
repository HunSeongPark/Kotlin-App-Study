package com.hunseong.hilt.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hunseong.hilt.R
import javax.inject.Inject

class NavigatorImpl @Inject constructor(private val activity: FragmentActivity) : Navigator {

    override fun navigate(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}