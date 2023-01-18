package com.example.weatherforecastapp.features.favorites_feature.repository

import com.example.weatherforecastapp.data.WeatherDatabaseDao
import com.example.weatherforecastapp.features.favorites_feature.model.FavoriteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val weatherDatabaseDao: WeatherDatabaseDao) {
    // GET ALL FAVORITE LIST
    fun getAllFavorite(): Flow<List<FavoriteModel>> =
        weatherDatabaseDao.getAllFavorites()

    // GET FAVORITE BY CITY
    suspend fun getFavoriteByCity(city: String) = weatherDatabaseDao.getFavoriteByCity(city)

    // ADD FAVORITE
    suspend fun addFavorite(favoriteModel: FavoriteModel) =
        weatherDatabaseDao.insertFavorite(favoriteModel)

    // UPDATE FAVORITE
    suspend fun updateFavorite(favoriteModel: FavoriteModel) =
        weatherDatabaseDao.updateFavorite(favoriteModel)

    // DELETE FAVORITE
    suspend fun deleteFavorite(favoriteModel: FavoriteModel) =
        weatherDatabaseDao.deleteFavorite(favoriteModel)

    // DELETE ALL
    suspend fun deleteAllFavorite() = weatherDatabaseDao.deleteAllFavorite()
}