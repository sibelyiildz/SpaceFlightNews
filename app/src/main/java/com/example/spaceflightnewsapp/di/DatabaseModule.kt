package com.example.spaceflightnewsapp.di

import android.content.Context
import androidx.room.Room
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsDao
import com.example.spaceflightnewsapp.data.local.SpaceFlightNewsDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module


val dataBaseModule = module {
    single { provideDatabase(get()) }
    single { provideDao(get()) }
    single { provideGson() }
}

fun provideDatabase(context: Context): SpaceFlightNewsDatabase {
    return Room.databaseBuilder(
        context,
        SpaceFlightNewsDatabase::class.java,
        "space_flight_news.db"
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

fun provideDao(database: SpaceFlightNewsDatabase): SpaceFlightNewsDao {
    return database.getSpaceFlightNewsDao()
}

fun provideGson(): Gson = GsonBuilder().create()

