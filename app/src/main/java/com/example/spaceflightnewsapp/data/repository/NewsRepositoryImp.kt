package com.example.spaceflightnewsapp.data.repository

import com.example.spaceflightnewsapp.data.remote.RemoteDataSource
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.domain.repository.NewsRepository

class NewsRepositoryImp(
    private val localDataSource: RemoteDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {

    override suspend fun getArticles(): ArticlesResponse {
        return remoteDataSource.getArticles()
    }
}