package com.example.weatherforecastapp.global_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.utils.AppColors


@Composable
fun CustomTopBar(
    weatherData: WeatherModel,
    isInMainScreen: Boolean = true,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            if (isInMainScreen) {
                Text(
                    text = "${weatherData.city.name}, ${weatherData.city.country}",
                    fontWeight = FontWeight.Bold,
                    color = AppColors.mBlack
                )
            } else {
                Box() {

                }
            }

        },
        actions = {
            if (isInMainScreen) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(24.dp),
                    tint = AppColors.mBlack
                )
            } else {
                Box() {

                }
            }
        },
        navigationIcon = {
            if (isInMainScreen) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "location",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 8.dp)
                )
            } else {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrow_back")
            }

        },
        backgroundColor = AppColors.mWhite, elevation = 0.dp,
    )

}