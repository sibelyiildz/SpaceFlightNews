package com.example.spaceflightnewsapp.ui.newslist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.spaceflightnewsapp.NavGraphDirections
import com.example.spaceflightnewsapp.base.BaseFragment
import com.example.spaceflightnewsapp.databinding.FragmentNewsBinding
import com.example.spaceflightnewsapp.util.Result
import com.example.spaceflightnewsapp.util.extension.dialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txt.setOnClickListener {
            findNavController().navigate(NavGraphDirections.actionNewsDetailFragment())
        }

        viewModel.fetchNews()
        viewModel.news.observe(viewLifecycleOwner) { response ->
            setLoading(response is Result.Loading)
            when (response) {
                is Result.Success -> {
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
}