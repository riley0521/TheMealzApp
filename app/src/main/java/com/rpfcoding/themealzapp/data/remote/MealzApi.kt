package com.rpfcoding.themealzapp.data.remote

import retrofit2.http.GET

interface MealzApi {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse
}