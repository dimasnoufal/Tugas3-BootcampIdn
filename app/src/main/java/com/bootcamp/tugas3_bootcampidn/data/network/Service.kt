package com.bootcamp.tugas3_bootcampidn.data.network

import com.bootcamp.tugas3_bootcampidn.data.network.api.NewsApi
import com.bootcamp.tugas3_bootcampidn.utils.Constant
import com.bootcamp.tugas3_bootcampidn.utils.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Service {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val NewsService: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}