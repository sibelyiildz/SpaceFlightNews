package com.example.spaceflightnewsapp.di

import com.example.spaceflightnewsapp.data.local.LocaleDataSource
import com.example.spaceflightnewsapp.data.remote.RemoteDataSource
import com.example.spaceflightnewsapp.data.repository.NewsRepositoryImp
import com.example.spaceflightnewsapp.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImp(get(), get()) }
    single { RemoteDataSource(get()) }
    single { LocaleDataSource(get()) }
}