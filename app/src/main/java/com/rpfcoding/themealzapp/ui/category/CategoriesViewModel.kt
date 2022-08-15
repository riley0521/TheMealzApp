package com.rpfcoding.themealzapp.ui.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpfcoding.themealzapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getAllCategories().data?.toString()?.let { Log.d("F", it) }
        }
    }
}