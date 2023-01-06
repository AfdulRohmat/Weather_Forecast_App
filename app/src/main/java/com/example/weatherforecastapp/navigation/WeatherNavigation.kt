package com.example.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecastapp.features.mainscreen_feature.MainScreen
import com.example.weatherforecastapp.features.mainscreen_feature.view_model.MainScreenViewModel
import com.example.weatherforecastapp.features.splashscreen_feature.view.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherAppScreens.SplashScreen.name) {
        // DEFINE ALL POSSIBLE SCREEN THAT APP WILL HAVE

        composable(WeatherAppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(WeatherAppScreens.MainScreen.name) {
            val mainScreenViewModel  = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController, mainScreenViewModel)
        }
    }

}