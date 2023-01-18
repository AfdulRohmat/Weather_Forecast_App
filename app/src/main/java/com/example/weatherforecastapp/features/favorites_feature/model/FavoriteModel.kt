package com.example.weatherforecastapp.features.favorites_feature.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteModel(
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "country")
    val country: String,
)
