package com.example.weatherforecastapp.features.favorites_feature.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.features.favorites_feature.model.FavoriteModel
import com.example.weatherforecastapp.features.favorites_feature.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    private val _favoriteList = MutableStateFlow<List<FavoriteModel>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    init {
        getAllFavorite()
    }

    // GET ALL FAVORITE LIST
    private fun getAllFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.getAllFavorite().distinctUntilChanged().collect { favoriteList ->
                if (favoriteList.isEmpty()) {
                    Log.d("favoriteList", "getAllFavorite: Empty List")


                } else {
                    _favoriteList.value = favoriteList
                    Log.d("favoriteList", "getAllFavorite: ${_favoriteList.value}")
                }
            }
        }
    }

    // ADD FAVORITE INT DB
    fun addFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            favoriteRepository.addFavorite(favoriteModel)
        }
    }

    // UPDATE FAVORITE
    fun updateFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            favoriteRepository.updateFavorite(favoriteModel)
        }
    }

    // DELETE FAVORITE BY ID
    fun deleteFavorite(favoriteModel: FavoriteModel) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(favoriteModel)
        }
    }

    // DELETE ALL FAVORITE LIST
    fun deleteAllFavoriteList() {
        viewModelScope.launch {
            favoriteRepository.deleteAllFavorite()
        }
    }


}