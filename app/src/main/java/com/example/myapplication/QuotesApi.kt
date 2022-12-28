package com.example.coroutineexamples

import com.example.myapplication.Article
import com.example.myapplication.MyNewsData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("v2/top-headlines")
    fun getMyQuotes(
        @Query("country")country:String,@Query("category")category:String,@Query("apiKey")apiKey:String
    ):Call<MyNewsData>
}