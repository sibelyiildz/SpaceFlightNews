package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.data.remote.model.ArticlesResponse
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import com.example.spaceflightnewsapp.util.Resource

class GetNewsUseCase constructor(
    private val repository: NewsRepository,
) : BaseUseCase<Unit, ArticlesResponse?>() {

    override suspend fun execute(request: Unit): Resource<ArticlesResponse?> {
        return try {
            Resource.Success(repository.getArticles())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

}