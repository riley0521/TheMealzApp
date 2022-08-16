package com.rpfcoding.themealzapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: String,
    val name: String,
    val thumbUrl: String,
    val description: String
) : Parcelable