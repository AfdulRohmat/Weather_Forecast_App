package com.example.weatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherforecastapp.features.favorites_feature.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDatabaseDao

}