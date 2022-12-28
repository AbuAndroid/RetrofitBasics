package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class NewsActivity : AppCompatActivity() {
    var uiTvTitle:TextView? = null
    var uiTvName:TextView?=null
    var uiIvimage:ImageView? = null
    var uiTvDescription:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpUi()
        val title = intent.getStringExtra("title")
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")

        uiTvName?.text = name
        uiTvTitle?.text = title
        uiIvimage?.let {
            Glide.with(it).load(image).apply(
                RequestOptions().diskCacheStrategy(
                DiskCacheStrategy.ALL)).into(uiIvimage!!)
        }
        uiTvDescription?.text = description
    }

    private fun setUpUi() {
        uiTvTitle = findViewById(R.id.uiNewsTvTitle)
        uiTvName = findViewById(R.id.uiNewsTvName)
        uiIvimage = findViewById(R.id.uiNewsIvimage)
        uiTvDescription = findViewById(R.id.uiNewsTvDescription)
    }
}