package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineexamples.Retrofithelper
import com.example.myapplication.Constants.Companion.API_KEY
import com.example.myapplication.Constants.Companion.CATEGORY
import com.example.myapplication.Constants.Companion.COUNTRY
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
            onItemClick = ::showDataToNewActivity
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
        GlobalScope.launch(Dispatchers.IO) {
            val news = Retrofithelper.newsInstance.getMyQuotes(COUNTRY, CATEGORY, API_KEY)
            news.enqueue(object : Callback<MyNewsData> {
                override fun onResponse(call: Call<MyNewsData>, response: Response<MyNewsData>) {
                    val news = response.body()
                    if (news != null) {
                        news.articles?.let { newsAdapter.onNewsListChanged(it) }
                    }

                    Log.d("fjkjk",news?.articles.toString())
                }

                override fun onFailure(call: Call<MyNewsData?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }


    }

    private fun showDataToNewActivity(article: Article){

        val newlist:ArrayList<Article> = arrayListOf<Article>()
        newlist.addAll(listOf(article))
        val title = article.title
        val name = article.author
        val image = article.urlToImage
        val decription = article.description

        val bundle = Bundle()
        bundle.putStringArrayList("list",newlist)
        val intent = Intent(this,NewsActivity::class.java)
        intent.putExtra("title",article)
        intent.putExtra("name",name)
        intent.putExtra("image",image)
        intent.putExtra("description",decription)

        startActivity(intent)
    }
    private fun makeToast(article: Article) {
        Toast.makeText(this@MainActivity, article.title, Toast.LENGTH_LONG).show()
    }
}