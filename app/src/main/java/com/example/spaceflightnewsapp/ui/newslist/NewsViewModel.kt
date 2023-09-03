package com.example.spaceflightnewsapp.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.domain.usecase.GetNewsUseCase
import com.example.spaceflightnewsapp.util.Resource
import com.example.spaceflightnewsapp.util.Result
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _news = MutableLiveData<Result<ArticlesResponse>>()
    val news: LiveData<Result<ArticlesResponse>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            _news.postValue(Result.Loading)
            when (val response = getNewsUseCase.execute(Unit)) {
                is Resource.Failure -> {
                    _news.postValue(Result.Error(response.error))
                }

                is Resource.Success -> {
                    _news.postValue(Result.Success(response.data))
                }
            }
        }
    }
}