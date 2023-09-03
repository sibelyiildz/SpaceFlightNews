package com.example.spaceflightnewsapp.ui.newsdetail

import android.os.Bundle
import android.view.View
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailFragment :
    BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {

    private val viewModel: NewsDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}