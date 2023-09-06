package com.example.spaceflightnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SpaceFlightNewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpaceFlightNewsDatabase : RoomDatabase() {
    abstract fun getSpaceFlightNewsDao(): SpaceFlightNewsDao
}