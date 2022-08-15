package com.rpfcoding.themealzapp.data.remote

import com.squareup.moshi.Json

data class CategoryDto(
    @field:Json(name = "idCategory") val id: String,
    @field:Json(name = "strCategory") val name: String,
    @field:Json(name = "strCategoryThumb") val thumbUrl: String,
    @field:Json(name = "strCategoryDescription") val description: String
)
