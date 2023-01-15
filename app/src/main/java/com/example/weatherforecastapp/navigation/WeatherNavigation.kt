package com.example.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecastapp.features.about_feature.view.AboutScreen
import com.example.weatherforecastapp.features.favorites_feature.view.FavoriteScreen
import com.example.weatherforecastapp.features.mainscreen_feature.MainScreen
import com.example.weatherforecastapp.features.mainscreen_feature.view_model.MainScreenViewModel
import com.example.weatherforecastapp.features.search_feature.view.SearchScreen
import com.example.weatherforecastapp.features.setting_feature.view.SettingScreen
import com.example.weatherforecastapp.features.splashscreen_feature.view.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherAppScreens.SplashScreen.name) {
        // DEFINE ALL POSSIBLE SCREEN THAT APP WILL HAVE

        composable(WeatherAppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        val route = WeatherAppScreens.MainScreen.name
        composable(
            "$route/{city}", arguments = listOf(
                navArgument(name = "city", builder = {
                    type = NavType.StringType
                })
            )
        ) { navBackEntry ->
            navBackEntry.arguments?.getString("city").let { cityValue ->
                val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
                MainScreen(navController = navController, mainScreenViewModel, city = cityValue)
            }

        }

        composable(WeatherAppScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(WeatherAppScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }

        composable(WeatherAppScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(WeatherAppScreens.SettingsScreen.name) {
            SettingScreen(navController = navController)
        }
    }

}