package com.example.coroutineexamples

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Retrofithelper {

    val baseUrl ="https://newsapi.org/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }


}