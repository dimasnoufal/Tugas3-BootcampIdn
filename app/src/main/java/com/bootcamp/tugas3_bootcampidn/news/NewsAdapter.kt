package com.bootcamp.tugas3_bootcampidn.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.tugas3_bootcampidn.R
import com.bootcamp.tugas3_bootcampidn.databinding.ItemRowNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter(private val newsList: List<ArticlesItem>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemRowNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem) {
            binding.apply {
                Glide.with(context)
                    .load(data.urlToImage)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgNews)
                tvJudul.text = data.title
                tvPenulis.text = data.author
                tvTanggalPosting.text = data.publishedAt
                binding.cardNews.setOnClickListener {
                    val detail = Intent(itemView.context, DetailNewsActivity::class.java).
                    putExtra(DetailNewsActivity.EXTRA_NEWS, newsList[adapterPosition])
                    itemView.context.startActivity(detail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding = ItemRowNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(newsList[position])
    }
}