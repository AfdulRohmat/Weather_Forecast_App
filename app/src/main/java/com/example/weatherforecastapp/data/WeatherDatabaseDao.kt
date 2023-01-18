package com.example.weatherforecastapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherforecastapp.features.favorites_feature.model.FavoriteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDatabaseDao {
    @Query("SELECT * from favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteModel>>

    @Query("SELECT * from favorite_table where city =:city")
    suspend fun getFavoriteByCity(city: String): FavoriteModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteModel: FavoriteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteModel: FavoriteModel)

    @Delete
    suspend fun deleteFavorite(favoriteModel: FavoriteModel)

    @Query("DELETE from favorite_table")
    suspend fun deleteAllFavorite()


}