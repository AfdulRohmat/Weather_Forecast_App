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

    var data: MutableState<DataOrException<WeatherModel, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    init {
        getAllWeatherData("Loderesan")
    }

    private fun getAllWeatherData(cityQuery: String) {

        viewModelScope.launch {
            if (cityQuery.isEmpty()) return@launch

            data.value.loading = true
            data.value = weatherRepository.getWeatherData(cityQuery)

            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }

        Log.d("DATA", "getAllWeatherData: ${data.value.data?.city?.name}")
    }
}