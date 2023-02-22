package com.bootcamp.tugas3_bootcampidn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.tugas3_bootcampidn.adapter.NewsAdapter
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityMainBinding
import com.bootcamp.tugas3_bootcampidn.api.ApiConfig
import com.bootcamp.tugas3_bootcampidn.data.network.handler.NetworkResult
import com.bootcamp.tugas3_bootcampidn.model.news.ArticlesItem
import com.bootcamp.tugas3_bootcampidn.model.news.NewsResponse
import com.bootcamp.tugas3_bootcampidn.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
//    private val newsAdapter by lazy {
//        NewsAdapter()
//    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.newsList.observe(this@MainActivity) { res ->
            when(res){
                is NetworkResult.Loading -> {
                    handleUi(
                        recyclerView = false,
                        progressbar = true,
                        errorTv = false,
                    )
                }
                is NetworkResult.Error -> {
                    binding.errorText.text = res.errorMessage
                    handleUi(
                        recyclerView = false,
                        progressbar = false,
                        errorTv = true,
                    )
                }
                is NetworkResult.Success ->{
                    val newsAdapter = NewsAdapter(res.data?.articles as List<ArticlesItem>, this@MainActivity)
                    binding.rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = newsAdapter
                    }

                    handleUi(
                        recyclerView = true,
                        progressbar = false,
                        errorTv = false,
                    )
                }
            }
        }

//        ApiConfig.getService().getUsers().enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                if (response.isSuccessful) {
//                    val responseNews = response.body()
//                    val dataNews = responseNews?.articles
//                    val newsAdapter = NewsAdapter(dataNews as List<ArticlesItem>, this@MainActivity)
//                    binding.rvNews.apply {
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        setHasFixedSize(true)
//                        adapter = newsAdapter
//                    }
//                } else {
//                    Toast.makeText(this@MainActivity, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//        })

    }
    private fun handleUi(
        recyclerView: Boolean,
        progressbar: Boolean,
        errorTv: Boolean
    ){
        binding.apply {
            rvNews.isVisible = recyclerView
            progressBar.isVisible = progressbar
            errorText.isVisible = errorTv
        }
    }



}