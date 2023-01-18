package com.example.weatherforecastapp.features.mainscreen_feature.repository

import android.util.Log
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {
    private val dataOrException =
        DataOrException<WeatherModel, Boolean, Exception>()

    suspend fun getWeatherData(cityQuery: String): DataOrException<WeatherModel, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = weatherApi.getWeatherData(cityQuery)

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (e: Exception) {
            Log.d("ERROR GET DATA", "getWeatherData: $e")
            dataOrException.error = e

        }

        return dataOrException
    }
}