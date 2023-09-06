package com.example.spaceflightnewsapp

import android.app.Application
import com.example.spaceflightnewsapp.di.dataBaseModule
import com.example.spaceflightnewsapp.di.networkModule
import com.example.spaceflightnewsapp.di.repositoryModule
import com.example.spaceflightnewsapp.di.useCaseModule
import com.example.spaceflightnewsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@NewsApplication)
            modules(networkModule, dataBaseModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }

}