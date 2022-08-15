package com.rpfcoding.themealzapp.domain.repository

import com.rpfcoding.themealzapp.domain.model.Category
import com.rpfcoding.themealzapp.util.Resource

interface MainRepository {

    suspend fun getAllCategories(): Resource<List<Category>>
}