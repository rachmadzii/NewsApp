package com.android.news.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.news.R
import com.android.news.adapter.NewsAdapter
import com.android.news.models.News
import com.android.news.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.hide()

        progress.visibility = View.GONE

        btnSearch.setOnClickListener {
            val search = etSearch.text.toString().toLowerCase()
            val getData: Call<News>

            if (search.equals("tech") || search.equals("technology")) {
                getData = ConfigNetwork.getRetrofit().getTechLast()
                getDataNews(getData)
            }
            else if (search.equals("sport") || search.equals("sports")) {
                getData = ConfigNetwork.getRetrofit().getSportLast()
                getDataNews(getData)
            }
            else if (search.equals("game") || search.equals("games")) {
                getData = ConfigNetwork.getRetrofit().getGameLast()
                getDataNews(getData)
            }
            else if (search.equals("film")) {
                getData = ConfigNetwork.getRetrofit().getFilmLast()
                getDataNews(getData)
            }
            else {
                status.text = "Sorry, Not Found."
                listNews.visibility = View.INVISIBLE
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getDataNews(getData: Call<News>){
        progress.visibility = View.VISIBLE
        status.text = "Latest News"

        if(isConnect()) {
            getData.enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    progress.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    Log.d("response server", response.message())
                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        val status = response.body()?.status
                        if (status == "ok") {
                            val data = response.body()?.articles
                            listNews.adapter = NewsAdapter(data)
                            listNews.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
        else {
            progress.visibility = View.GONE
            Toast.makeText(this,"Your device not connect with internet", Toast.LENGTH_SHORT).show()
        }
    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return  connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}