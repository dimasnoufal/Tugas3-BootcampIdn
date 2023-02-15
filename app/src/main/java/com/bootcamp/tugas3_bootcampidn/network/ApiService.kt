package com.bootcamp.tugas3_bootcampidn.network

import com.bootcamp.tugas3_bootcampidn.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=id&apiKey=f044de69086e45198f8406be2094a229")
    fun getUsers(): Call<NewsResponse>
}