package com.bootcamp.tugas3_bootcampidn.data

import android.util.Log
import com.bootcamp.tugas3_bootcampidn.data.network.api.NewsApi
import com.bootcamp.tugas3_bootcampidn.data.network.handler.NetworkResult
import com.bootcamp.tugas3_bootcampidn.model.news.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val newsApi: NewsApi) {
    suspend fun getNews(): Flow<NetworkResult<NewsResponse>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val news = newsApi.getNews()

            // request data successful
            if (news.isSuccessful){
                val responseBody = news.body()

                // if data empty
                if (responseBody?.articles?.isEmpty() == true){
                    emit(NetworkResult.Error("News List Not Found"))
                }else{
                    emit(NetworkResult.Success(responseBody!!))
                }

            }else{

                // request data failed
                Log.d("apiServiceError", "statusCode:${news.code()}, message:${news.message()}")
                emit(NetworkResult.Error("Failed to fetch data from server."))
            }

        }catch (err:Exception){
            err.printStackTrace()
            Log.d("remoteError", "${err.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }.flowOn(Dispatchers.IO)



}