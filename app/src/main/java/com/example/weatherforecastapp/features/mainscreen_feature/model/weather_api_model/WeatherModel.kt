package com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model

data class WeatherModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherObject>,
    val message: Int
)