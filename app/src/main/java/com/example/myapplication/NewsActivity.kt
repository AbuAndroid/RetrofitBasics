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
    private var uiTvTitle:TextView? = null
    private var uiTvName:TextView?=null
    private var uiIvImage:ImageView? = null
    private var uiTvDescription:TextView? = null

    companion object{
        const val ARTICLE_NAME = "article"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpUi()
        val myIntent = intent
        val article = myIntent.getParcelableExtra<Article>(ARTICLE_NAME)

       uiTvName?.text = article?.author.toString()
        uiTvTitle?.text = article?.title.toString()
        uiIvImage?.let {
            Glide.with(it).load(article?.urlToImage.toString()).apply(
                RequestOptions().diskCacheStrategy(
                DiskCacheStrategy.ALL)).into(uiIvImage!!)
        }
        uiTvDescription?.text = article?.description.toString()
    }

    private fun setUpUi() {
        uiTvTitle = findViewById(R.id.uiNewsTvTitle)
        uiTvName = findViewById(R.id.uiTvNewsName)
        uiIvImage = findViewById(R.id.uiIvNewsImage)
        uiTvDescription = findViewById(R.id.uiNewsTvDescription)
    }
}