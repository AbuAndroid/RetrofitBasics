package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Network.Retrofithelper
import com.example.myapplication.Constants.Companion.API_KEY
import com.example.myapplication.Constants.Companion.CATEGORY
import com.example.myapplication.Constants.Companion.COUNTRY
import com.example.myapplication.Models.Article
import com.example.myapplication.Models.MyNewsData
import com.example.myapplication.NewsActivity.Companion.ARTICLE_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            newsList = arrayListOf(),
            onItemClick = this::makeToast
        )
    }
    private var recyclerview :RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        getNewsFromServer()
    }

    private fun setUpUi() {
        recyclerview = findViewById(R.id.uiRvRecyclerView)
        recyclerview?.adapter = newsAdapter
    }

    private fun getNewsFromServer(){
        GlobalScope.launch(Dispatchers.Main) {
            val news = Retrofithelper.newsInstance.getMyQuotes(COUNTRY, CATEGORY, API_KEY)
            news.enqueue(object : Callback<MyNewsData> {
                override fun onResponse(call: Call<MyNewsData>, response: Response<MyNewsData>) {
                    val news = response.body()
                    if (news != null) {
                        news.articles?.let { newsAdapter.onNewsListChanged(it) }
                    }
                }

                override fun onFailure(call: Call<MyNewsData?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }


    }

    private fun showDataToNewActivity(article: Article){
        val intent = Intent(this@MainActivity,NewsActivity::class.java)
        intent.putExtra(ARTICLE_NAME,article)
        Log.d("dd",article.toString())
        startActivity(intent)
    }
    private fun makeToast(article: Article) {
        Toast.makeText(this@MainActivity, article.title, Toast.LENGTH_LONG).show()
    }
}