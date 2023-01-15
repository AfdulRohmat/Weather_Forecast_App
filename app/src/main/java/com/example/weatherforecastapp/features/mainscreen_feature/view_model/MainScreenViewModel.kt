package com.example.weatherforecastapp.features.mainscreen_feature.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    suspend fun getAllWeatherData(cityQuery: String): DataOrException<WeatherModel, Boolean, Exception> {
        return weatherRepository.getWeatherData(cityQuery)
    }
}