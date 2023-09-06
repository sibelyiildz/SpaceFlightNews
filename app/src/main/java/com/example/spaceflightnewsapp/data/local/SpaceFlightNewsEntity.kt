package com.example.spaceflightnewsapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "space_flight_news_entity")
data class SpaceFlightNewsEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int
) : Parcelable