package com.example.spaceflightnewsapp.di

import com.example.spaceflightnewsapp.data.remote.repository.NewsRepositoryImp
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImp(get()) }
}