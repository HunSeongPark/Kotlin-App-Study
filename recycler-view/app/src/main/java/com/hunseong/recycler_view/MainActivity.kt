package com.hunseong.recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adapter: ConcatAdapter by lazy {
        ConcatAdapter(headerAdapter, flowerAdapter)
    }

    private val flowerAdapter: FlowerAdapter by lazy {
        FlowerAdapter {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(FLOWER_KEY, it)
            }
            startActivity(intent)
        }
    }

    private val headerAdapter: HeaderAdapter by lazy {
        HeaderAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        flowerAdapter.submitList(addFlowerList())
    }

    private fun addFlowerList() : List<Flower> {
        return listOf(
            Flower(
                id = 1,
                name = resources.getString(R.string.flower1_name),
                image = R.drawable.rose,
                description = resources.getString(R.string.flower1_description)
            ),
            Flower(
                id = 2,
                name = resources.getString(R.string.flower2_name),
                image = R.drawable.freesia,
                description = resources.getString(R.string.flower2_description)
            ),
            Flower(
                id = 3,
                name = resources.getString(R.string.flower3_name),
                image = R.drawable.lily,
                description = resources.getString(R.string.flower3_description)
            ),
            Flower(
                id = 4,
                name = resources.getString(R.string.flower4_name),
                image = R.drawable.sunflower,
                description = resources.getString(R.string.flower4_description)
            ),
            Flower(
                id = 5,
                name = resources.getString(R.string.flower5_name),
                image = R.drawable.peony,
                description = resources.getString(R.string.flower5_description)
            ),
            Flower(
                id = 6,
                name = resources.getString(R.string.flower6_name),
                image = R.drawable.daisy,
                description = resources.getString(R.string.flower6_description)
            ),
            Flower(
                id = 7,
                name = resources.getString(R.string.flower7_name),
                image = R.drawable.lilac,
                description = resources.getString(R.string.flower7_description)
            ),
            Flower(
                id = 8,
                name = resources.getString(R.string.flower8_name),
                image = R.drawable.marigold,
                description = resources.getString(R.string.flower8_description)
            ),
            Flower(
                id = 9,
                name = resources.getString(R.string.flower9_name),
                image = R.drawable.poppy,
                description = resources.getString(R.string.flower9_description)
            ),
            Flower(
                id = 10,
                name = resources.getString(R.string.flower10_name),
                image = R.drawable.daffodil,
                description = resources.getString(R.string.flower10_description)
            ),
            Flower(
                id = 11,
                name = resources.getString(R.string.flower11_name),
                image = R.drawable.dahlia,
                description = resources.getString(R.string.flower11_description)
            )
        )
    }

    companion object {
        const val FLOWER_KEY = "flower_key"
    }
}