package com.bootcamp.tugas3_bootcampidn.data.network.api

import com.bootcamp.tugas3_bootcampidn.model.news.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=us&category=business&apiKey=f044de69086e45198f8406be2094a229")
    suspend fun getNews() : Response<NewsResponse>

}