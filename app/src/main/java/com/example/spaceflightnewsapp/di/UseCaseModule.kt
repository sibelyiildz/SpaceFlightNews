package com.example.spaceflightnewsapp.di

import com.example.spaceflightnewsapp.domain.usecase.GetNewsDetailUseCase
import com.example.spaceflightnewsapp.domain.usecase.GetNewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNewsUseCase(get()) }
    factory { GetNewsDetailUseCase(get()) }
}