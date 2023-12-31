package com.example.spaceflightnewsapp.ui.newslist

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.domain.model.UpdateReadingListModel
import com.example.spaceflightnewsapp.domain.usecase.DeleteNewsFromRoomUseCase
import com.example.spaceflightnewsapp.domain.usecase.GetNewsUseCase
import com.example.spaceflightnewsapp.domain.usecase.InsertNewsToRoomUseCase
import com.example.spaceflightnewsapp.extension.toLiveData
import com.example.spaceflightnewsapp.extension.toSingleEvent
import com.example.spaceflightnewsapp.util.Resource
import com.example.spaceflightnewsapp.util.Result
import com.example.spaceflightnewsapp.util.countDownTimer
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val insertNewsToRoomUseCase: InsertNewsToRoomUseCase,
    private val deleteNewsFromRoomUseCase: DeleteNewsFromRoomUseCase,
) : ViewModel() {

    private val _news = MutableLiveData<Result<ViewState>>()
    val news = _news.toLiveData()

    private val _updateReadingList = MutableLiveData<Result<UpdateReadingListModel>>()
    val updateReadingList = _updateReadingList.toSingleEvent()

    private var viewState = ViewState()
    private var timer: CountDownTimer? = null

    companion object {
        private const val ONE_MINUTE = 60000L
        private const val ONE_SECOND = 1000L
    }

    init {
        timer = countDownTimer(millisInFuture = ONE_MINUTE,
            countDownInterval = ONE_SECOND,
            onTick = {},
            onFinish = {
                fetchNews(true)
            })

    }

    fun fetchNews(isFromTimer: Boolean = false) {
        timer?.cancel()
        viewModelScope.launch {
            if (isFromTimer.not()) _news.postValue(Result.Loading)
            when (val response = getNewsUseCase.execute(Unit)) {
                is Resource.Success -> {
                    if (isFromTimer) {
                        if (viewState.news != response.data.results) {
                            viewState =
                                viewState.copy(news = response.data.results, isNewNews = true)
                            _news.postValue(Result.Success(viewState))
                        }
                    } else {
                        viewState = viewState.copy(news = response.data.results, isNewNews = false)
                        _news.postValue(Result.Success(viewState))
                    }
                }

                is Resource.Failure -> {
                    if (isFromTimer.not()) _news.postValue(Result.Error(response.error))
                }
            }
            timer?.start()
        }
    }

    fun updateReadingList(newsId: Int, isSave: Boolean) {
        if (isSave) {
            saveNewsToRoom(newsId)
        } else {
            deleteNewsFromRoom(newsId)
        }
    }

    private fun saveNewsToRoom(id: Int) {
        viewModelScope.launch {
            _updateReadingList.postValue(Result.Loading)
            when (val response =
                insertNewsToRoomUseCase.execute(InsertNewsToRoomUseCase.Request(id))) {
                is Resource.Success -> {
                    _updateReadingList.postValue(Result.Success(UpdateReadingListModel(id, true)))
                }

                is Resource.Failure -> {
                    _updateReadingList.postValue(Result.Error(response.error))
                }
            }
        }
    }

    private fun deleteNewsFromRoom(id: Int) {
        viewModelScope.launch {
            _updateReadingList.postValue(Result.Loading)
            when (val response =
                deleteNewsFromRoomUseCase.execute(DeleteNewsFromRoomUseCase.Request(id))) {
                is Resource.Success -> {
                    _updateReadingList.postValue(Result.Success(UpdateReadingListModel(id, false)))
                }

                is Resource.Failure -> {
                    _updateReadingList.postValue(Result.Error(response.error))
                }
            }
        }
    }

    fun timerCancel() {
        timer?.cancel()
    }

    override fun onCleared() {
        timer?.cancel()
        timer = null
        super.onCleared()
    }

    data class ViewState(val news: List<NewsModel>? = null, val isNewNews: Boolean = false)

}