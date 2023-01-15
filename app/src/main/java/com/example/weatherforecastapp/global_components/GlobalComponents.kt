package com.example.weatherforecastapp.global_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.navigation.WeatherAppScreens
import com.example.weatherforecastapp.utils.AppColors


@Composable
fun CustomTopBar(
    title: String,
    isInMainScreen: Boolean = true,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = {
            Text(
                text = title, fontWeight = FontWeight.Bold, color = AppColors.mBlack
            )

        },
        actions = {
            if (isInMainScreen) {
                // SEARCH ICON
                IconButton(onClick = {
                    onAddActionClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier.size(24.dp),
                        tint = AppColors.mBlack
                    )
                }

                // DROPDOWN ICON
                IconButton(onClick = {
                    showDialog.value = !showDialog.value

                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "dropdown",
                        modifier = Modifier.size(24.dp),
                        tint = AppColors.mBlack
                    )
                }

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
                    tint = AppColors.mBlack,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 8.dp)
                )
            } else {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "arrow_back",
                        tint = AppColors.mBlack
                    )

                }
            }

        },
        backgroundColor = AppColors.mWhite, elevation = 0.dp,
    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    val dropdownItems = listOf("About", "Favorites", "Settings")
    val expanded = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                showDialog.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            dropdownItems.forEachIndexed { index, eachItem ->
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    showDialog.value = false
                    navController.navigate(
                        when (eachItem) {
                            "About" -> WeatherAppScreens.AboutScreen.name
                            "Favorites" -> WeatherAppScreens.FavoriteScreen.name
                            else -> WeatherAppScreens.SettingsScreen.name

                        }
                    )

                }) {
                    Icon(
                        imageVector = when (eachItem) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings

                        }, contentDescription = null, tint = AppColors.mBlack
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = eachItem, fontWeight = FontWeight.W300, color = AppColors.mBlack)

                }
            }


        }

    }


}
