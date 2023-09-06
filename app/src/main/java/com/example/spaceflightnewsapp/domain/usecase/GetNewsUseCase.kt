package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.base.BaseUseCase
import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import com.example.spaceflightnewsapp.util.Resource

class GetNewsUseCase constructor(
    private val repository: NewsRepository,
) : BaseUseCase<Unit, ArticlesResponse>() {

    override suspend fun execute(request: Unit): Resource<ArticlesResponse> {
        return try {
            val newsFromLocal = repository.getNews()
            val newsFromRemote = repository.getArticles()

            newsFromRemote.results?.map { news ->
                val localNews = newsFromLocal.find { it.id == news.id }
                news.isSave = localNews != null
            }
            Resource.Success(newsFromRemote)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

}