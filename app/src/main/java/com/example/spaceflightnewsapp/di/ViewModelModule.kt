package com.example.spaceflightnewsapp.di

import com.example.spaceflightnewsapp.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NewsViewModel)
}
