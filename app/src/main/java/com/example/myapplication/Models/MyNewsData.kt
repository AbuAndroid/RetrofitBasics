package com.example.myapplication.Models

data class MyNewsData(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)