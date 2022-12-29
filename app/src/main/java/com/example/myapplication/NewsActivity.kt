package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Models.Article

class NewsActivity : AppCompatActivity() {
    var uiTvTitle:TextView? = null
    var uiTvName:TextView?=null
    var uiIvimage:ImageView? = null
    var uiTvDescription:TextView? = null

    companion object{
        val ARTICLE_NAME = "article"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpUi()
        val myIntent = intent
        val article = myIntent.getParcelableExtra<Article>(ARTICLE_NAME)

       uiTvName?.text = article?.author.toString()
        uiTvTitle?.text = article?.title.toString()
        uiIvimage?.let {
            Glide.with(it).load(article?.urlToImage.toString()).apply(
                RequestOptions().diskCacheStrategy(
                DiskCacheStrategy.ALL)).into(uiIvimage!!)
        }
        uiTvDescription?.text = article?.description.toString()
    }

    private fun setUpUi() {
        uiTvTitle = findViewById(R.id.uiNewsTvTitle)
        uiTvName = findViewById(R.id.uiNewsTvName)
        uiIvimage = findViewById(R.id.uiNewsIvimage)
        uiTvDescription = findViewById(R.id.uiNewsTvDescription)
    }
}