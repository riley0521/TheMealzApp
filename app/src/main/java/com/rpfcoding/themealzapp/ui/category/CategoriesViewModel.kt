package com.rpfcoding.themealzapp.ui.category

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpfcoding.themealzapp.domain.repository.MainRepository
import com.rpfcoding.themealzapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    var state by mutableStateOf(CategoriesState())
        private set

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when(val result = repository.getAllCategories()) {
                is Resource.Error -> {
                    state = state.copy(
                        errorMsg = result.message
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        categories = result.data ?: emptyList(),
                        errorMsg = null
                    )
                }
                is Resource.Loading -> Unit
            }

            state = state.copy(isLoading = false)
        }
    }
}