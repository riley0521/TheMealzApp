package com.rpfcoding.themealzapp.data.mapper

import com.rpfcoding.themealzapp.data.remote.CategoryDto
import com.rpfcoding.themealzapp.domain.model.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        thumbUrl = thumbUrl,
        description = description
    )
}