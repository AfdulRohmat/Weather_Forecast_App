package com.example.weatherforecastapp.features.mainscreen_feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecastapp.features.mainscreen_feature.view_model.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val weatherData = mainScreenViewModel.data.value.data


    Column(modifier = Modifier.fillMaxSize()) {

        if (mainScreenViewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
            if (weatherData != null) {
                Text(text = weatherData.city.name)
            }
        }


    }

}