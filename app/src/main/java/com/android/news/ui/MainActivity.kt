package com.android.news.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.news.R
import com.android.news.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val fragmentAdapter =
            PageAdapter(supportFragmentManager)

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        searchNews.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}