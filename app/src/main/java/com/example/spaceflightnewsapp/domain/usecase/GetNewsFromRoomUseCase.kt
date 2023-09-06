package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.base.BaseUseCase
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsEntity
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import com.example.spaceflightnewsapp.util.Resource

class GetNewsFromRoomUseCase constructor(
    private val repository: NewsRepository,
) : BaseUseCase<Unit, List<SpaceFlightNewsEntity>>() {

    override suspend fun execute(request: Unit): Resource<List<SpaceFlightNewsEntity>> {
        return try {
            Resource.Success(repository.getNews())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

}