package com.example.myapplication.Models

import com.example.myapplication.Models.Article

data class MyNewsData(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)