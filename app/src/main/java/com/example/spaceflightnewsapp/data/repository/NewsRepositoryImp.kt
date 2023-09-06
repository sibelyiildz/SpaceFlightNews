package com.example.spaceflightnewsapp.data.repository

import com.example.spaceflightnewsapp.data.local.LocaleDataSource
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsEntity
import com.example.spaceflightnewsapp.data.remote.RemoteDataSource
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.domain.repository.NewsRepository

class NewsRepositoryImp(
    private val localDataSource: LocaleDataSource,
    private val remoteDataSource: RemoteDataSource
) : NewsRepository {

    override suspend fun getArticles(): ArticlesResponse {
        return remoteDataSource.getArticles()
    }

    override suspend fun getArticleDetail(id: Int): NewsModel {
        return remoteDataSource.getArticleDetail(id)
    }

    override suspend fun insertNews(spaceFlightNewsEntity: SpaceFlightNewsEntity) {
        localDataSource.insertNews(spaceFlightNewsEntity)
    }

    override suspend fun getNews(): List<SpaceFlightNewsEntity> {
        return localDataSource.getNews()
    }
}