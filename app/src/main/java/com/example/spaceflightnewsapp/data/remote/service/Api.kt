package com.example.spaceflightnewsapp.data.remote.service

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import retrofit2.http.GET

interface Api {

    @GET("articles/")
    suspend fun getArticles(): ArticlesResponse

}