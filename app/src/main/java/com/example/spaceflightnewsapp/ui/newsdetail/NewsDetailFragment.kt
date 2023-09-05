package com.example.spaceflightnewsapp.ui.newsdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.databinding.FragmentNewsDetailBinding
import com.example.spaceflightnewsapp.extension.errorDialog
import com.example.spaceflightnewsapp.extension.setImageUrl
import com.example.spaceflightnewsapp.util.DateFormat
import com.example.spaceflightnewsapp.util.Result
import com.example.spaceflightnewsapp.util.convertDateToFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailFragment :
    BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {

    private val viewModel: NewsDetailViewModel by viewModel()
    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBarLayout.setNavigationOnClickListener { findNavController().popBackStack() }

        viewModel.fetchNewsDetail(args.id)
        viewModel.newsDetail.observe(viewLifecycleOwner, ::newsDetailObserver)
    }

    private fun newsDetailObserver(response: Result<NewsModel>) {
        setLoading(response is Result.Loading)
        when (response) {
            is Result.Success -> {
                response.data?.let { setUI(it) }
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

    private fun setUI(response: NewsModel) {
        with(binding) {
            newsImage.setImageUrl(requireContext(), response.image_url)
            collapsingToolbar.title = response.title
            summary.text = response.summary
            newsSite.text = response.news_site
            publishedDate.text =
                convertDateToFormat(response.published_at, DateFormat.DAY_MONTH_WITH_HOUR_MINUTE)

        }

    }
}