package com.rpfcoding.themealzapp.ui.category

import com.rpfcoding.themealzapp.domain.model.Category

data class CategoriesState(
    val categories: List<Category> = emptyList(),
    val errorMsg: String? = null,
    val isLoading: Boolean = false
)
