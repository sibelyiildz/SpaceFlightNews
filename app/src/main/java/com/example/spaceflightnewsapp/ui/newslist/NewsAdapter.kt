package com.example.spaceflightnewsapp.ui.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnewsapp.NavGraphDirections
import com.example.spaceflightnewsapp.R
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.databinding.ListItemNewsBinding
import com.example.spaceflightnewsapp.extension.setImageUrl
import com.example.spaceflightnewsapp.util.DateFormat
import com.example.spaceflightnewsapp.util.convertDateToFormat

class NewsAdapter(private val saveButtonOnClick: (id: Int, isSave: Boolean) -> Unit) :
    ListAdapter<NewsModel, NewsAdapter.ViewHolder>(DIFF), Filterable {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var originalList = listOf<NewsModel>()

    override fun submitList(list: List<NewsModel>?) {
        if (this.originalList.isEmpty()) {
            this.originalList = list.orEmpty()
        }
        super.submitList(list)
    }

    override fun submitList(list: List<NewsModel>?, commitCallback: Runnable?) {
        if (this.originalList.isEmpty()) {
            this.originalList = list.orEmpty()
        }
        super.submitList(list, commitCallback)
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

    fun updateItem(id: Int, isSave: Boolean) {
        currentList.find { it.id == id }?.isSave = isSave
        notifyItemChanged(currentList.indexOf(currentList.find { it.id == id }))
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
                newsImage.setImageUrl(root.context, data.image_url)
                addReadingList.text =
                    root.context.getString(if (data.isSave) R.string.remove_reading_list else R.string.add_reading_list)

                addReadingList.setOnClickListener {
                    saveButtonOnClick.invoke(data.id, data.isSave.not())
                }
                root.setOnClickListener {
                    it.findNavController()
                        .navigate(NavGraphDirections.actionNewsDetailFragment(data.id))
                }
            }
        }

    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            results.values = if (constraint.isNullOrEmpty()) {
                originalList
            } else {
                originalList.filter {
                    it.title.orEmpty().lowercase().contains(constraint.toString().lowercase())
                }
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(list = filterResults?.values as? List<NewsModel> ?: return)
        }
    }

}