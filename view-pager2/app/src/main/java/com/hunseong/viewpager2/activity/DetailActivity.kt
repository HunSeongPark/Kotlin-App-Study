package com.hunseong.viewpager2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hunseong.viewpager2.adapter.DetailPagerAdapter
import com.hunseong.viewpager2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailAdapter: DetailPagerAdapter by lazy {
        DetailPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        pager.adapter = detailAdapter
        val tabName = listOf("First", "Second", "Third", "Fourth")
        TabLayoutMediator(indicator, pager) { tab, position ->
            tab.text = tabName[position]
        }.attach()
    }
}