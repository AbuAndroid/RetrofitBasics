package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineexamples.QuotesApi
import com.example.coroutineexamples.Retrofithelper
import com.example.myapplication.Constants.Companion.API_KEY
import com.example.myapplication.Constants.Companion.CATEGORY
import com.example.myapplication.Constants.Companion.COUNTRY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var result:List<Article>? = null
    lateinit var adapter:RecyclerAdapter
    lateinit var recyclerview : RecyclerView
    lateinit var quotesApi: QuotesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
        setUpRecyclerView()
        GlobalScope.launch(Dispatchers.Main) {
            quotesApi = Retrofithelper.getInstance().create(QuotesApi::class.java)
            result = quotesApi.getMyQuotes(COUNTRY,CATEGORY,API_KEY).body()?.articles
            adapter = result?.let { RecyclerAdapter(it) }!!
            adapter.notifyDataSetChanged()
            recyclerview.adapter = adapter
        }
    }


    private fun setUpUi() {
        recyclerview = findViewById(R.id.uiRvRecyclerView)
    }

    private fun setUpRecyclerView() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
    }
}