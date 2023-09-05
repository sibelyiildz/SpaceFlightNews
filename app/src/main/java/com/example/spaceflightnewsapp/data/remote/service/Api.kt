package com.example.spaceflightnewsapp.data.remote.service

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("articles/")
    suspend fun getArticles(): ArticlesResponse

    @GET("articles/{id}/")
    suspend fun getArticleDetail(@Path("id") id: Int): NewsModel
}