package com.android.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.news.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.btnBack

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        val image = intent.getStringExtra("image")
        val date = intent.getStringExtra("date")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val desc = intent.getStringExtra("desc")

        newsDate.text = date
        newsTitle.text = title
        newsAuthor.text = author
        newsDesc.text = desc

        Glide.with(this).load(image).into(newsImage)

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}