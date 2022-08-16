package com.rpfcoding.themealzapp.ui.category_list

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

    private fun fetchCategories() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when (val result = repository.getAllCategories()) {
                is Resource.Error -> {
                    state.copy(
                        errorMsg = result.message
                    )
                }
                is Resource.Success -> {
                    state.copy(
                        categories = result.data ?: emptyList(),
                        errorMsg = null
                    )
                }
            }

            state = state.copy(isLoading = false)
        }
    }
}