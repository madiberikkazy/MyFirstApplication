package com.example.myapplication.model

data class CatalogItem(
    val id: Int,
    val title: String,
    val desc: String,
    val price: Double,
    val isFav: Boolean,
    val name: String
)