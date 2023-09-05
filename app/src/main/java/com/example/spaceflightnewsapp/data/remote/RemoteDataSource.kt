package com.example.spaceflightnewsapp.data.remote

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.data.remote.service.Api

class RemoteDataSource(private val api: Api) {

    suspend fun getArticles(): ArticlesResponse {
        return api.getArticles()
    }

    suspend fun getArticleDetail(id: Int): NewsModel {
        return api.getArticleDetail(id)
    }
}