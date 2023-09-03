package com.example.spaceflightnewsapp.di

import com.example.spaceflightnewsapp.ui.newsdetail.NewsDetailViewModel
import com.example.spaceflightnewsapp.ui.newslist.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NewsViewModel)
    viewModelOf(::NewsDetailViewModel)
}
