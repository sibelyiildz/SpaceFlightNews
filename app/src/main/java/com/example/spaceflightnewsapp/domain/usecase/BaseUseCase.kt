package com.example.spaceflightnewsapp.domain.usecase

import com.example.spaceflightnewsapp.util.Resource

abstract class BaseUseCase<in R, T> {

    abstract suspend fun execute(request: R): Resource<T>

}