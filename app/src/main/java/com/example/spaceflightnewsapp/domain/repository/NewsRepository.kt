package com.example.spaceflightnewsapp.domain.repository

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse

interface NewsRepository {

    suspend fun getArticles(): ArticlesResponse
}