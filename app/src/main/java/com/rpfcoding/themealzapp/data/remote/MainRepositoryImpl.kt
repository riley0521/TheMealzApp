package com.rpfcoding.themealzapp.data.remote

import com.rpfcoding.themealzapp.data.mapper.toCategory
import com.rpfcoding.themealzapp.domain.model.Category
import com.rpfcoding.themealzapp.domain.repository.MainRepository
import com.rpfcoding.themealzapp.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MealzApi
) : MainRepository {

    override suspend fun getAllCategories(): Resource<List<Category>> {
        return try {
            val categories = api.getAllCategories().categories.map { it.toCategory() }

            Resource.Success(data = categories)
        } catch (e: IOException) {
            Resource.Error(message = "Unknown error occurred")
        } catch (e: HttpException) {
            Resource.Error(message = "Unknown error occurred")
        }
    }
}