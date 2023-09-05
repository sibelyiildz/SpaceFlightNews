package com.example.spaceflightnewsapp.domain.repository

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.model.NewsModel

interface NewsRepository {

    suspend fun getArticles(): ArticlesResponse

    suspend fun getArticleDetail(id: Int): NewsModel
}