package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.base.BaseUseCase
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsEntity
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import com.example.spaceflightnewsapp.util.Resource

class InsertNewsToRoomUseCase constructor(
    private val repository: NewsRepository,
) : BaseUseCase<InsertNewsToRoomUseCase.Request, Unit>() {

    override suspend fun execute(request: Request): Resource<Unit> {
        return try {
            Resource.Success(repository.insertNews(SpaceFlightNewsEntity(request.id)))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    data class Request(val id: Int)

}