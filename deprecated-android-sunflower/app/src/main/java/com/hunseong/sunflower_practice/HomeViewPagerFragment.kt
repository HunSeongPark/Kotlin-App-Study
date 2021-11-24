package com.hunseong.sunflower_practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hunseong.sunflower_practice.adapters.SunflowerPagerAdapter
import com.hunseong.sunflower_practice.adapters.SunflowerPagerAdapter.Companion.MY_INDEX
import com.hunseong.sunflower_practice.adapters.SunflowerPagerAdapter.Companion.PLANT_LIST_INDEX
import com.hunseong.sunflower_practice.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IndexOutOfBoundsException

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Data binding
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        // viewpager adapter setting
        viewPager.adapter = SunflowerPagerAdapter(this)

        // tab layout icon and text setting
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }


    // get icon resource
    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_INDEX -> R.drawable.my_tab_selector
            PLANT_LIST_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    // get title string
    private fun getTabTitle(position: Int) : String? {
        return when (position) {
            MY_INDEX -> getString(R.string.my_title)
            PLANT_LIST_INDEX -> getString(R.string.plant_list_title)
            else -> null
        }
    }
}