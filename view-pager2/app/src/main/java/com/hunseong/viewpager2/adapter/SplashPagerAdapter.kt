package com.hunseong.viewpager2.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hunseong.viewpager2.R
import com.hunseong.viewpager2.fragment.SplashPagerFragment

class SplashPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SplashPagerFragment.newInstance(context.getString(R.string.str_1))
            1 -> SplashPagerFragment.newInstance(context.getString(R.string.str_2))
            2 -> SplashPagerFragment.newInstance(context.getString(R.string.str_3))
            else -> SplashPagerFragment.newInstance(context.getString(R.string.str_4))
        }
    }
}