package com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)