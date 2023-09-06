package com.example.spaceflightnewsapp.ui.newslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.spaceflightnewsapp.R
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsBinding
import com.example.spaceflightnewsapp.domain.model.UpdateReadingListModel
import com.example.spaceflightnewsapp.extension.errorDialog
import com.example.spaceflightnewsapp.util.Result
import com.example.spaceflightnewsapp.util.SpaceItemDecorationVertical
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(::onHandleSaveButtonClick) }
    private val spaceDecorator by lazy { SpaceItemDecorationVertical() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setListeners()

        viewModel.fetchNews()
        viewModel.news.observe(viewLifecycleOwner, ::newsObserver)
        viewModel.updateReadingList.observe(viewLifecycleOwner, ::updateReadingListObserver)
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
            }

        }
    }

    private fun updateReadingListObserver(response: Result<UpdateReadingListModel>) {
        setLoading(response is Result.Loading)
        when (response) {
            is Result.Success -> {
                response.data?.let {
                    adapter.updateItem(id = it.id, isSave = it.isSave)
                }
            }

            is Result.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
            }

            Result.Loading -> {
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
    }

    private fun setListeners() {
        binding.searchView.setOnQueryTextListener(searchQueryTextListener)
    }

    private fun onHandleSaveButtonClick(id: Int, isSave: Boolean) {
        viewModel.updateReadingList(id, isSave)
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