package com.hunseong.recycler_view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    private fun initViews() {
        val flower: Flower? = intent.getParcelableExtra(MainActivity.FLOWER_KEY)

        flower?.let {
            findViewById<ImageView>(R.id.flower_iv).setImageResource(it.image)
            findViewById<TextView>(R.id.title_tv).text = it.name
            findViewById<TextView>(R.id.description_tv).text = it.description
        } ?: kotlin.run {
            Toast.makeText(this, "not available information.", Toast.LENGTH_SHORT).show()
        }
    }
}