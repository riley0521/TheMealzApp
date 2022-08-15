package com.rpfcoding.themealzapp.di

import com.rpfcoding.themealzapp.data.remote.MainRepositoryImpl
import com.rpfcoding.themealzapp.data.remote.MealzApi
import com.rpfcoding.themealzapp.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(api: MealzApi): MainRepository = MainRepositoryImpl(api)
}