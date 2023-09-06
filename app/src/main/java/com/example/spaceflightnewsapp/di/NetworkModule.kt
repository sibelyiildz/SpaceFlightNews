package com.example.spaceflightnewsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.spaceflightnewsapp.BuildConfig
import com.example.spaceflightnewsapp.data.remote.service.Api
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideApi(get()) }
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient().newBuilder().connectTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(ChuckerInterceptor(context))
        .build()
}

fun provideApi(okHttpClient: OkHttpClient): Api {
    val gson = GsonBuilder().setLenient().create()
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build().create(Api::class.java)
}

