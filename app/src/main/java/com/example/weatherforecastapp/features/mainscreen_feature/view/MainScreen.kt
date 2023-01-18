package com.example.weatherforecastapp.features.mainscreen_feature

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
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
import coil.compose.rememberImagePainter
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.R.*
import com.example.weatherforecastapp.data.DataOrException
import com.example.weatherforecastapp.features.mainscreen_feature.view_model.MainScreenViewModel
import com.example.weatherforecastapp.global_components.CustomTopBar
import com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model.WeatherModel
import com.example.weatherforecastapp.features.mainscreen_feature.model.weather_api_model.WeatherObject
import com.example.weatherforecastapp.navigation.WeatherAppScreens
import com.example.weatherforecastapp.utils.AppColors
import com.example.weatherforecastapp.utils.formatDate
import com.example.weatherforecastapp.utils.formatDateTime
import com.example.weatherforecastapp.utils.formatDecimals
import java.util.regex.Pattern

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    city: String?
) {

    // EXAMPLE TO GET INIT FUNCTION ( JUST LIKE initState() in flutter). GOOD EXAMPLE FOR PARSING REPOSITORY FUNCTION THAT HAVE A PARAMETER
    val weatherData =
        produceState<DataOrException<WeatherModel, Boolean, Exception>>(initialValue = DataOrException(
            loading = true
        ), producer = {
            value = mainScreenViewModel.getAllWeatherData(city.toString())

        }).value

    Column(modifier = Modifier.fillMaxSize()) {

        if (weatherData.loading == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = AppColors.mBlue)
            }
        } else {
            if (weatherData.data != null) {
                MainScreenScaffold(weatherData = weatherData.data!!, navController = navController)
            }
        }


    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenScaffold(weatherData: WeatherModel, navController: NavController) {
    Scaffold(topBar = {
        CustomTopBar(
            title = "${weatherData.city.name}, ${weatherData.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherAppScreens.SearchScreen.name)

            }
        )

    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.mWhite),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // BOX STATUS
            BoxWeatherData(weatherData = weatherData)

            // ADDITIONAL INFORMATION
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomStatusInfo(
                    data = weatherData.list[0].main.humidity.toString(),
                    iconId = R.drawable.humidity,
                    unit = "%"
                )
                CustomStatusInfo(
                    data = weatherData.list[0].main.pressure.toString(),
                    iconId = R.drawable.pressure,
                    unit = "psi"
                )
                CustomStatusInfo(
                    data = weatherData.list[0].wind.speed.toString(),
                    iconId = R.drawable.wind,
                    unit = "mph"
                )

            }

            // SUNSET AND SUNRISE ROW
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomSunriseSunset(
                    data = formatDateTime(weatherData.city.sunrise),
                    iconId = R.drawable.sunrise,

                    )
                CustomSunriseSunset(
                    data = formatDateTime(weatherData.city.sunset),
                    iconId = R.drawable.sunset,

                    )
            }

            // DAILY WEATHER
            Text(
                text = "Daily Weather",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                color = AppColors.mBlack,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            // LAZY LIST COLUM LIST OF WEATHER
            LazyColumn() {
                items(items = weatherData.list) { weatherItem ->
                    DailyWeatherTile(weatherData = weatherItem)
                }
            }


        }


    }
}


@Composable
fun DailyWeatherTile(weatherData: WeatherObject) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherData.weather[0].icon}.png"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = AppColors.mLightPurple)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ICON AND DESC
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Weather Icon
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(color = AppColors.mDarkPurple)
                ) {
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = "weather_icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp)
                    )
                }

                // DAY AND DESCRIPTION
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = formatDate(weatherData.dt),
                        modifier = Modifier.padding(end = 4.dp),
                        color = AppColors.mBlack,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = weatherData.dt_txt.split(" ")[1],
                        modifier = Modifier.padding(end = 4.dp),
                        color = AppColors.mBlack,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = weatherData.weather[0].description,
                        color = AppColors.mBlack,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }

            // WEATHER CELSIUS
            Text(
                text = "${weatherData.main.temp} °C",
                color = AppColors.mBlack,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )


        }

    }
}

@Composable
fun CustomSunriseSunset(data: String, iconId: Int) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = AppColors.mGrey)
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = "icon_app",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = data,
                modifier = Modifier.padding(end = 4.dp),
                color = AppColors.mBlack,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )

        }

    }

}


@Composable
fun CustomStatusInfo(data: String, iconId: Int, unit: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = AppColors.mGrey)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = "icon_app",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = data,
                modifier = Modifier.padding(bottom = 4.dp),
                color = AppColors.mBlack,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = unit,
                color = AppColors.mBlack,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }

    }


}


//@Preview(showBackground = true)
@Composable
fun BoxWeatherData(
    weatherData: WeatherModel,
) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherData.list[0].weather[0].icon}.png"
    val WHITESPACE = Pattern.compile("\\s+")
    val date_text = WHITESPACE.split(weatherData.list[0].dt_txt.trim())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mLightBlue, AppColors.mBlue
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
            Text(
                text = formatDate(weatherData.list[0].dt),
                modifier = Modifier.padding(bottom = 16.dp),
                color = AppColors.mWhite
            )

            // STATUS WEATHER
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ICON
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = "icon_app",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(100.dp)
                )
                Column() {
                    Text(
                        text = "${formatDecimals(weatherData.list[0].main.temp)} °C",
                        color = AppColors.mWhite,
                        fontWeight = FontWeight.Light,
                        fontSize = 24.sp
                    )
                    Text(
                        text = weatherData.list[0].weather[0].main,
                        color = AppColors.mWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text(
                        text = weatherData.list[0].weather[0].description,
                        color = AppColors.mWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }

            }

            // LAST UPDATE
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Last Updated ${date_text[1]}", color = AppColors.mWhite
                )
            }


        }

    }
}


