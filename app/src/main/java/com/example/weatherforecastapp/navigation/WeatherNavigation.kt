package com.example.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecastapp.features.splashscreen_feature.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherAppScreens.SplashScreen.name) {
        // DEFINE ALL POSSIBLE SCREEN THAT APP WILL HAVE

        composable(WeatherAppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }

}