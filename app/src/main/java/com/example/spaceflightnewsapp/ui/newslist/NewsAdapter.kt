package com.example.spaceflightnewsapp.ui.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnewsapp.NavGraphDirections
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.databinding.ListItemNewsBinding
import com.example.spaceflightnewsapp.extension.setImageUrl
import com.example.spaceflightnewsapp.util.DateFormat
import com.example.spaceflightnewsapp.util.convertDateToFormat

class NewsAdapter() : ListAdapter<NewsModel, NewsAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    inner class ViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: NewsModel) {
            with(binding) {
                newsTitle.text = data.title
                newsDescription.text = data.summary
                newsSite.text = data.news_site
                publishedDate.text =
                    convertDateToFormat(data.published_at, DateFormat.DAY_MONTH_WITH_HOUR_MINUTE)
                newsImage.setImageUrl(binding.root.context, data.image_url)

                root.setOnClickListener {
                    it.findNavController().navigate(NavGraphDirections.actionNewsDetailFragment())
                }
            }
        }

    }


}