package com.example.myapplication.Network

import com.example.myapplication.Models.MyNewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("v2/top-headlines")
    fun getMyQuotes(
        @Query("country")country:String,@Query("category")category:String,@Query("apiKey")apiKey:String
    ):Call<MyNewsData>
}