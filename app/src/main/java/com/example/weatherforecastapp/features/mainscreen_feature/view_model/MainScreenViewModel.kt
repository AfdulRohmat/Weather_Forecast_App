package com.example.weatherforecastapp.features.mainscreen_feature.view_model

import androidx.lifecycle.ViewModel
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.features.mainscreen_feature.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    suspend fun getAllWeatherData(cityQuery: String): DataOrException<WeatherModel, Boolean, Exception> {
        return weatherRepository.getWeatherData(cityQuery)
    }
}