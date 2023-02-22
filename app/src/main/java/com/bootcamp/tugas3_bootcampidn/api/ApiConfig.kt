package com.bootcamp.tugas3_bootcampidn.api

import com.bootcamp.tugas3_bootcampidn.data.network.api.NewsApi
import com.bootcamp.tugas3_bootcampidn.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
//    fun getInterceptor() : OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//        return  okHttpClient
//    }
//    fun getRetrofit() : Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .client(getInterceptor())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    fun getService() = getRetrofit().create(NewsApi::class.java)
}