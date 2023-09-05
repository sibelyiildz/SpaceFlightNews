package com.example.spaceflightnewsapp.data.remote

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.service.Api

class RemoteDataSource(private val api: Api) {

    suspend fun getArticles(): ArticlesResponse {
        return api.getArticles()
    }
}