package com.example.myapplication

data class MyNewsData(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)