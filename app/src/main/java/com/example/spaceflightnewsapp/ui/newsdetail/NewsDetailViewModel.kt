package com.example.spaceflightnewsapp.ui.newsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.domain.usecase.GetNewsDetailUseCase
import com.example.spaceflightnewsapp.extension.setThreadingValue
import com.example.spaceflightnewsapp.extension.toLiveData
import com.example.spaceflightnewsapp.util.Resource
import com.example.spaceflightnewsapp.util.Result
import kotlinx.coroutines.launch

class NewsDetailViewModel(private val getNewsDetailUseCase: GetNewsDetailUseCase) : ViewModel() {

    private val _newsDetail = MutableLiveData<Result<NewsModel>>()
    val newsDetail = _newsDetail.toLiveData()

    fun fetchNewsDetail(id: Int) {
        viewModelScope.launch {
            _newsDetail.setThreadingValue(Result.Loading)
            when (val response =
                getNewsDetailUseCase.execute(GetNewsDetailUseCase.Request(id = id))) {
                is Resource.Success -> {
                    _newsDetail.setThreadingValue(Result.Success(response.data))
                }

                is Resource.Failure -> {
                    _newsDetail.setThreadingValue(Result.Error(response.error))
                }
            }
        }
    }
}