package com.example.spaceflightnewsapp.domain.repository

import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsEntity
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.model.NewsModel

interface NewsRepository {

    suspend fun getArticles(): ArticlesResponse

    suspend fun getArticleDetail(id: Int): NewsModel

    suspend fun insertNews(spaceFlightNewsEntity: SpaceFlightNewsEntity)

    suspend fun getNews(): List<SpaceFlightNewsEntity>
}