package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.base.BaseUseCase
import com.example.spaceflightnewsapp.data.remote.model.NewsModel
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import com.example.spaceflightnewsapp.util.Resource

class GetNewsDetailUseCase constructor(
    private val repository: NewsRepository,
) : BaseUseCase<GetNewsDetailUseCase.Request, NewsModel>() {

    override suspend fun execute(request: Request): Resource<NewsModel> {
        return try {
            Resource.Success(repository.getArticleDetail(request.id))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    data class Request(val id: Int)

}