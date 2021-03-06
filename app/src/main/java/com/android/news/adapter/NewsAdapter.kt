package com.android.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.news.R
import com.android.news.models.Article
import com.android.news.ui.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(var data: ArrayList<Article>?) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.newsTitle
        val author = itemView.newsAuthor
        val desc = itemView.newsDesc
        val date = itemView.newsDate
        val img = itemView.newsImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val holder = NewsHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.title.text = data?.get(position)?.title
        holder.author.text = data?.get(position)?.author
        holder.desc.text = data?.get(position)?.description
        holder.date.text = data?.get(position)?.publishedAt
        holder.author.text = data?.get(position)?.author
        Glide.with(holder.itemView.context).load(data?.get(position)?.urlToImage).into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("image", data?.get(position)?.urlToImage)
            intent.putExtra("date", data?.get(position)?.publishedAt)
            intent.putExtra("title", data?.get(position)?.title)
            intent.putExtra("author", data?.get(position)?.author)
            intent.putExtra("desc", data?.get(position)?.description)

            holder.itemView.context.startActivity(intent)

        }
    }
}