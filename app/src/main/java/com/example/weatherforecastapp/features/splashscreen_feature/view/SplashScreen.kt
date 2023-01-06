package com.example.weatherforecastapp.features.splashscreen_feature.view

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.navigation.WeatherAppScreens
import com.example.weatherforecastapp.utils.AppColors
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    // DELAY FOR SEVERAL TIME THEN GO TO NEXT PAGE
    LaunchedEffect(key1 = true, block = {
        delay(300L)
        navController.navigate(WeatherAppScreens.MainScreen.name) {
            popUpTo(WeatherAppScreens.SplashScreen.name) {
                inclusive = true
            }
        }
    })

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ICON APPLICATION
        Image(
            painter = painterResource(id = R.drawable.icon_cuaca),
            contentDescription = "icon_app",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(260.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TEXT TITLE
        Text(
            text = "Weather Forecast",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.mBlack
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    }
}
