package com.example.spaceflightnewsapp.ui.newslist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsBinding
import com.example.spaceflightnewsapp.extension.dialog
import com.example.spaceflightnewsapp.util.Result
import com.example.spaceflightnewsapp.util.SpaceItemDecorationVertical
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter() }
    private val spaceDecorator by lazy { SpaceItemDecorationVertical() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setListeners()

        viewModel.fetchNews()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            setLoading(response is Result.Loading)
            when (response) {
                is Result.Success -> {
                    adapter.submitList(response.data?.results)
                    Log.v("LogTag", "Success")
                }

                is Result.Error -> {
                    Log.v("LogTag", "Error")
                    dialog {
                        setTitle("Hata")
                        setMessage("Bir sorun oluştu")
                        setCancelable(true)
                        setPositiveButton("Tamam", null)
                    }
                }

                Result.Loading -> {
                    Log.v("LogTag", "Success")
                }

            }
        }
    }

    private fun initialize(){
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.addItemDecoration(spaceDecorator)

    }

    private fun setListeners() {
        binding.searchView.setOnQueryTextListener(searchQueryTextListener)
    }

    private val searchQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            adapter.filter.filter(newText)
            return true
        }
    }

}