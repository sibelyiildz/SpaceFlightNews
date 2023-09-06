package com.example.spaceflightnewsapp.ui.newslist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.spaceflightnewsapp.R
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsBinding
import com.example.spaceflightnewsapp.extension.errorDialog
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
        viewModel.news.observe(viewLifecycleOwner, ::newsObserver)
    }

    private fun newsObserver(response: Result<NewsViewModel.ViewState>) {
        setLoading(response is Result.Loading)
        when (response) {
            is Result.Success -> {
                setUI(response.data)
            }

            is Result.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
            }

            Result.Loading -> {
                Log.v("LogTag", "Loading")
            }

        }
    }

    private fun setUI(data: NewsViewModel.ViewState?) {
        adapter.submitList(data?.news)
        if (binding.newsRecyclerView.computeVerticalScrollOffset() > 0) {
            Toast.makeText(
                requireContext(),
                getString(R.string.new_headlines_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initialize() {
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.addItemDecoration(spaceDecorator)

        binding.insertButton.setOnClickListener {
            viewModel.saveNewsToRoom(60)
        }
        binding.getButton.setOnClickListener {
            viewModel.fetchNewsFromRoom()
        }

        viewModel.saveNews.observe(viewLifecycleOwner) {
            Log.v("LogTag", "saveNews ->$it")
        }
        viewModel.fetchNewsFromRoom.observe(viewLifecycleOwner) {
            Log.v("LogTag", "fetchNewsFromRoom ->$it")
        }

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

    override fun onDestroyView() {
        viewModel.timerCancel()
        super.onDestroyView()
    }

}