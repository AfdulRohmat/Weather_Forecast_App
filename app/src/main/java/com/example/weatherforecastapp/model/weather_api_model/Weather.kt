package com.example.weatherforecastapp.model.weather_api_model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)