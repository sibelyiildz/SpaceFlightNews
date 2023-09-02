package com.example.spaceflightnewsapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.spaceflightnewsapp.di.networkModule
import com.example.spaceflightnewsapp.di.repositoryModule
import com.example.spaceflightnewsapp.di.useCaseModule
import com.example.spaceflightnewsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        GlobalContext.startKoin {
            androidContext(this@NewsApplication)
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak") // for debug app
        @JvmStatic
        var mContext: Context? = null
        fun getContext(): Context? {
            return mContext
        }
    }
}