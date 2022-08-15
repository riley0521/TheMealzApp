package com.rpfcoding.themealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.rpfcoding.themealzapp.ui.category.CategoriesScreen
import com.rpfcoding.themealzapp.ui.theme.TheMealzAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMealzAppTheme {
                CategoriesScreen()
            }
        }
    }
}