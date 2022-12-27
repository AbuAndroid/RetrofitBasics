package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineexamples.QuotesApi
import com.example.coroutineexamples.Retrofithelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var result:Any
    lateinit var displayList:ArrayList<Any>
    lateinit var title : String
    lateinit var name : String
    lateinit var descrip : String
    val API_KEY = "46258c880da44da6aae2748398106253"
    val COUNTRY = "us"
    val CATEGORY = "business"

    lateinit var recyclerview : RecyclerView
    lateinit var news : ArrayList<recycleNewsDataClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()

        val quotesApi = Retrofithelper.getInstance().create(QuotesApi::class.java)
        recyclerview.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch {
            result = quotesApi.getMyQuotes(COUNTRY,CATEGORY,API_KEY).body().articles
            displayList = result as ArrayList<Any>
        //            txtview.setText(result) .articles?.get(2)?.description.toString()
            news= arrayListOf<recycleNewsDataClass>(
                recycleNewsDataClass(
                    newsTitle = displayList.
                )
            )
        }
        setUpRecyclerView()

    }



    private fun setUpUi() {
        recyclerview = findViewById(R.id.uiRvRecyclerView)
    }
    private fun setUpRecyclerView() {


    }
}