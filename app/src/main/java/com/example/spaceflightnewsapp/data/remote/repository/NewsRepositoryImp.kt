package com.example.spaceflightnewsapp.data.remote.repository

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.service.Api
import com.example.spaceflightnewsapp.domain.repository.NewsRepository

class NewsRepositoryImp(private val api: Api) : NewsRepository {

    override suspend fun getArticles(): ArticlesResponse {
        return api.getArticles()
    }
}