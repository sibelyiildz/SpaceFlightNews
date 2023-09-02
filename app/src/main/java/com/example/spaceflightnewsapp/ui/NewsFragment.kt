package com.example.spaceflightnewsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsBinding
import com.example.spaceflightnewsapp.util.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchNews2()
        viewModel.news.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Error -> {
                    Log.v("LogTag", "Error")
                }

                Result.Loading -> {
                    Log.v("LogTag", "Loading")
                }

                is Result.Success -> {
                    Log.v("LogTag", "Success")
                }
            }
        }
    }
}