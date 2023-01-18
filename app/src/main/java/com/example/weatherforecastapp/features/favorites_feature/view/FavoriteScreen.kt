package com.example.weatherforecastapp.features.favorites_feature.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherforecastapp.features.favorites_feature.view_model.FavoriteViewModel
import com.example.weatherforecastapp.global_components.CustomTopBar
import com.example.weatherforecastapp.features.favorites_feature.model.FavoriteModel
import com.example.weatherforecastapp.navigation.WeatherAppScreens
import com.example.weatherforecastapp.utils.AppColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Favorite List",
                navController = navController,
                isInMainScreen = false
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            val favoriteList = favoriteViewModel.favoriteList.collectAsState().value

            if (favoriteList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Empty Data")

                }

            } else {
                LazyColumn {
                    items(items = favoriteList) { eachFavoriteItem ->

                        FavoriteCardTile(
                            favoriteModel = eachFavoriteItem,
                            navController = navController,
                            onIconTap = {
                                favoriteViewModel.deleteFavorite(eachFavoriteItem)
                            }
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun FavoriteCardTile(
    favoriteModel: FavoriteModel,
    navController: NavHostController,
    onIconTap: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = AppColors.mLightPurple)
            .clickable {
                navController.navigate(WeatherAppScreens.MainScreen.name + "/${favoriteModel.city}") {
                    popUpTo(0)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = favoriteModel.city)
            Text(text = favoriteModel.country, fontWeight = FontWeight.Bold)
            IconButton(onClick = {
                onIconTap()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = AppColors.mBlack,
                    modifier = Modifier.padding(end = 8.dp)
                )

            }

        }
    }


}
