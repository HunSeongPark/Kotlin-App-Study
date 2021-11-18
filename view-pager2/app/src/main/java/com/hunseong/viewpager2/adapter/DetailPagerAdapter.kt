package com.hunseong.viewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hunseong.viewpager2.fragment.FirstFragment
import com.hunseong.viewpager2.fragment.FourthFragment
import com.hunseong.viewpager2.fragment.SecondFragment
import com.hunseong.viewpager2.fragment.ThirdFragment

class DetailPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> FourthFragment()
        }
    }
}