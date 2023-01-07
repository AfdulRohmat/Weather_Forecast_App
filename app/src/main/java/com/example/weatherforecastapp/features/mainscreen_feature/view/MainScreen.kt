package com.example.weatherforecastapp.features.mainscreen_feature

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.R.*
import com.example.weatherforecastapp.features.mainscreen_feature.view_model.MainScreenViewModel
import com.example.weatherforecastapp.global_components.CustomTopBar
import com.example.weatherforecastapp.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.utils.AppColors

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val weatherData = mainScreenViewModel.data.value.data


    Column(modifier = Modifier.fillMaxSize()) {

        if (mainScreenViewModel.data.value.loading == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = AppColors.mBlue)
            }
        } else {
            if (weatherData != null) {
                MainScreenScaffold(weatherData = weatherData, navController = navController)
            }
        }


    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenScaffold(weatherData: WeatherModel, navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(
                weatherData = weatherData,
                navController = navController
            )

        }
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        // BOX STATUS
        BoxWeatherData()


    }
}

//@Preview(showBackground = true)
@Composable
fun BoxWeatherData() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mLightBlue,
                        AppColors.mBlue
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // DATE
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Senin, 20 Desember 2021", color = AppColors.mWhite)
                Text(text = "3.30 PM", color = AppColors.mWhite)
            }

            // STATUS WEATHER
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ICON
                Image(
                    painter = painterResource(id = R.drawable.icon_cuaca),
                    contentDescription = "icon_app",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp)
                )
                Column() {
                    Text(
                        text = "18 °C",
                        color = AppColors.mWhite,
                        fontWeight = FontWeight.Light,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Hujan Berawan",
                        color = AppColors.mWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

            }

            // LAST UPDATE
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Terakhir Update 3.00 PM",
                    modifier = Modifier.padding(end = 8.dp),
                    color = AppColors.mWhite
                )
                Icon(
                    painter = painterResource(id = drawable.refresh),
                    contentDescription = "refresh",
                    modifier = Modifier
                        .size(16.dp),
                    tint = AppColors.mWhite
                )

            }


        }

    }
}

