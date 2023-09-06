package com.example.spaceflightnewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SpaceFlightNewsDao {

    @Query("select * from space_flight_news_entity")
    fun getNews(): List<SpaceFlightNewsEntity>

    @Insert
    fun insertNews(spaceFlightNewsEntity: SpaceFlightNewsEntity)

    @Delete
    fun deleteNews(spaceFlightNewsEntity: SpaceFlightNewsEntity)


}