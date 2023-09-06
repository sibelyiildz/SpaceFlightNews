package com.example.spaceflightnewsapp.data.local

class LocaleDataSource(private val spaceFlightNewsDao: SpaceFlightNewsDao) {

    suspend fun getNews(): List<SpaceFlightNewsEntity> {
        return spaceFlightNewsDao.getNews()
    }
    suspend fun insertNews(spaceFlightNewsEntity: SpaceFlightNewsEntity) {
        return spaceFlightNewsDao.insertNews(spaceFlightNewsEntity)
    }

    suspend fun deleteNews(spaceFlightNewsEntity: SpaceFlightNewsEntity) {
        return spaceFlightNewsDao.deleteNews(spaceFlightNewsEntity)
    }

}