package com.example.weatherforecastapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherforecastapp.data.WeatherDatabase
import com.example.weatherforecastapp.data.WeatherDatabaseDao
import com.example.weatherforecastapp.network.WeatherApi
import com.example.weatherforecastapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // PROVIDE DATABASE DAO FRO ROOM DB
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDatabaseDao =
        weatherDatabase.weatherDao()

    // PROVIDE ROOM DB
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration().build()


    // BUILD RETROFIT
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

}