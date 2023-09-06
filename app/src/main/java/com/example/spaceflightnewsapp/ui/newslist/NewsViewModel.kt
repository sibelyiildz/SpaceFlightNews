package com.example.spaceflightnewsapp.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsEntity
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.domain.usecase.GetNewsFromRoomUseCase
import com.example.spaceflightnewsapp.domain.usecase.GetNewsUseCase
import com.example.spaceflightnewsapp.domain.usecase.InsertNewsToRoomUseCase
import com.example.spaceflightnewsapp.util.Resource
import com.example.spaceflightnewsapp.util.Result
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val insertNewsToRoomUseCase: InsertNewsToRoomUseCase,
    private val getNewsFromRoomUseCase: GetNewsFromRoomUseCase,
) : ViewModel() {

    private val _news = MutableLiveData<Result<ArticlesResponse>>()
    val news: LiveData<Result<ArticlesResponse>> = _news

    private val _saveNews = MutableLiveData<Result<Unit>>()
    val saveNews: LiveData<Result<Unit>> = _saveNews

    private val _fetchNewsFromRoom = MutableLiveData<Result<List<SpaceFlightNewsEntity>>>()
    val fetchNewsFromRoom: LiveData<Result<List<SpaceFlightNewsEntity>>> = _fetchNewsFromRoom

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

    fun saveNewsToRoom(id: Int) {
        viewModelScope.launch {
            _saveNews.postValue(Result.Loading)
            when (val response =
                insertNewsToRoomUseCase.execute(InsertNewsToRoomUseCase.Request(id))) {
                is Resource.Failure -> {
                    _saveNews.postValue(Result.Error(response.error))
                }

                is Resource.Success -> {
                    _saveNews.postValue(Result.Success(response.data))
                }
            }
        }
    }

    fun fetchNewsFromRoom() {
        viewModelScope.launch {
            _saveNews.postValue(Result.Loading)
            when (val response = getNewsFromRoomUseCase.execute(Unit)) {
                is Resource.Failure -> {
                    _fetchNewsFromRoom.postValue(Result.Error(response.error))
                }

                is Resource.Success -> {
                    _fetchNewsFromRoom.postValue(Result.Success(response.data))
                }
            }
        }
    }

}