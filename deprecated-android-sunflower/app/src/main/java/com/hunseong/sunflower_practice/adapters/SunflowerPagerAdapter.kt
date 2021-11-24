package com.hunseong.sunflower_practice.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hunseong.sunflower_practice.PlantListFragment
import java.lang.IndexOutOfBoundsException

class SunflowerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_INDEX to { GardenFragment() },
        PLANT_LIST_INDEX to { PlantListFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    companion object {
        const val MY_INDEX = 0
        const val PLANT_LIST_INDEX = 1
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}