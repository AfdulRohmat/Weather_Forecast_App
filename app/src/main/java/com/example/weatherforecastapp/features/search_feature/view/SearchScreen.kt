package com.example.weatherforecastapp.features.search_feature.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.weatherforecastapp.global_components.CustomTopBar
import com.example.weatherforecastapp.navigation.WeatherAppScreens
import com.example.weatherforecastapp.navigation.WeatherNavigation
import com.example.weatherforecastapp.utils.AppColors


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Search Location",
                navController = navController,
                isInMainScreen = false
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.mWhite)

        ) {
            SearchBar(modifier = Modifier.fillMaxWidth(), onSearch = { city ->
                Log.d("SearchBar", "SearchScreen: $city")
                navController.navigate(WeatherAppScreens.MainScreen.name + "/$city"){
                    popUpTo(WeatherAppScreens.SplashScreen.name + "/$city") {
                        inclusive = true
                    }
                }
            })

        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {

    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }

    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Search Location",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions

                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeholder, color = AppColors.mBlack) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = AppColors.mBlue,
            unfocusedBorderColor = AppColors.mBlack,
            cursorColor = AppColors.mBlack
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()

    )


}

