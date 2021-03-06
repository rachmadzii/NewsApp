package com.android.news.network

import com.android.news.models.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("everything?q=technology&sortBy=popularity&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getNewsTech(): Call<News>

    @GET("everything?q=tech&sortBy=publishedAt&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getTechLast(): Call<News>

    @GET("everything?q=games&sortBy=popularity&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getNewsGames(): Call<News>

    @GET("everything?q=game&sortBy=publishedAt&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getGameLast(): Call<News>

    @GET("everything?q=film&sortBy=popularity&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getNewsFilm(): Call<News>

    @GET("everything?q=film&sortBy=publishedAt&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getFilmLast(): Call<News>

    @GET("everything?q=sports&sortBy=popularity&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getNewsSports(): Call<News>

    @GET("everything?q=sport&sortBy=publishedAt&apiKey=2ac18dd1bafc4812bbb1605f426777f9")
    fun getSportLast(): Call<News>
}