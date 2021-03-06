package com.android.news.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.news.R
import com.android.news.adapter.NewsAdapter
import com.android.news.models.News
import com.android.news.network.ConfigNetwork
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        if(isConnect()){
            ConfigNetwork.getRetrofit().getNewsGames().enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {
                    progress?.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<News>,
                    response: Response<News>
                ) {
                    Log.d("response server", response.message())
                    if (response.isSuccessful) {
                        progress?.visibility = View.GONE

                        val status = response.body()?.status
                        if (status == "ok") {
                            val data = response.body()?.articles
                            view.listNewsGame.adapter = NewsAdapter(data)
                        }
                    }
                }
            })
        }
        else {
            progress?.visibility = View.GONE
            Toast.makeText(context,"Your device not connect with internet", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager = getActivity()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return  connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }
}