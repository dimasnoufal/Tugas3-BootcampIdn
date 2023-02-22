package com.bootcamp.tugas3_bootcampidn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bootcamp.tugas3_bootcampidn.data.RemoteDataSource
import com.bootcamp.tugas3_bootcampidn.data.Repository
import com.bootcamp.tugas3_bootcampidn.data.network.Service
import com.bootcamp.tugas3_bootcampidn.data.network.handler.NetworkResult
import com.bootcamp.tugas3_bootcampidn.model.news.NewsResponse
import kotlinx.coroutines.launch

class MainViewModel():ViewModel() {

    private val remoteService = Service.NewsService
    private val remote = RemoteDataSource(remoteService)
    private val repository = Repository(remote)

    private var _newsList:MutableLiveData<NetworkResult<NewsResponse>> = MutableLiveData()
    val newsList:LiveData<NetworkResult<NewsResponse>> =_newsList

    init {
        fetchNewsList()
    }

    private  fun fetchNewsList(){
        viewModelScope.launch {
            repository.remote.getNews().collect{res ->
                _newsList.value = res
            }
        }
    }

}