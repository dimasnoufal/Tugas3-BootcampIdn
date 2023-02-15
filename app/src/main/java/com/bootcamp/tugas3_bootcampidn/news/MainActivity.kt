package com.bootcamp.tugas3_bootcampidn.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityMainBinding
import com.bootcamp.tugas3_bootcampidn.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiConfig().getService().getUsers().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val responseNews = response.body()
                    val dataNews = responseNews?.articles
                    val newsAdapter = NewsAdapter(dataNews as List<ArticlesItem>, this@MainActivity)
                    binding.rvNews.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = newsAdapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

//        newsAdapter.setOnItemClickCallback(object: NewsAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: News) {
//                Toast.makeText(this@MainActivity, "Kamu memilih detail " + data.titleNews, Toast.LENGTH_SHORT).show()
//                val detail = Intent(this@MainActivity, DetailNewsActivity::class.java).
//                        putExtra(DetailNewsActivity.EXTRA_NEWS, data.imgNews).
//                        putExtra(DetailNewsActivity.EXTRA_NEWS, data.titleNews).
//                        putExtra(DetailNewsActivity.EXTRA_NEWS, data.author).
//                        putExtra(DetailNewsActivity.EXTRA_NEWS, data.dateNews).
//                        putExtra(DetailNewsActivity.EXTRA_NEWS, data.description)
//                startActivity(detail)
//            }
//        })
    }
}