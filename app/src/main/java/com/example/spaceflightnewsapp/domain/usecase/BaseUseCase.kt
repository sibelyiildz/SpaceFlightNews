package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in R, T> {

    abstract suspend fun execute(request: R): Resource<T>

    suspend operator fun invoke(request: R): Resource<T> {
        return withContext(Dispatchers.IO) {
            execute(request)
        }
    }
}