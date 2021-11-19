package com.hunseong.infinite_scroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter()
    }

    private var reloadCount = 0
    private var itemIndex = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        loadItem()
    }

    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastCompleteVisiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val lastItemPosition = (recyclerView.adapter)!!.itemCount - 1


                if (!recyclerView.canScrollVertically(1) && lastCompleteVisiblePosition == lastItemPosition && !isLoading) {
                    isLoading = true
                    adapter.showLoading()

                    Handler().postDelayed({
                        loadItem()
                        isLoading = false
                    }, 2000)

                }
            }
        })
    }

    private fun loadItem() {
        val list = ArrayList<String>()
        for (i in 1..10) {
            list.add("item $itemIndex")
            itemIndex++
        }
        adapter.setList(list)
        adapter.notifyItemRangeChanged(reloadCount * 10, 10)
        reloadCount ++
    }
}