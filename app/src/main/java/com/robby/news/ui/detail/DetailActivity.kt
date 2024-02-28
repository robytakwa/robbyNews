package com.robby.news.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.robby.news.R
import com.robby.news.databinding.ActivityDetailBinding
import com.robby.news.databinding.CustomToolbarBinding
import com.robby.news.source.news.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailActivity() }
}

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private lateinit var toolbar : CustomToolbarBinding
    private val viewModel: DetailViewModel by viewModel()
    private val detail by lazy { intent.getSerializableExtra("detail") as ArticleModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = binding.toolbar
        setContentView(binding.root)

        setSupportActionBar( toolbar.container )
        supportActionBar!!.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        detail.let {
            viewModel.find(it)
            val web = binding.webView
            web.loadUrl( it.url!! )
            web.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressTop.visibility = View.GONE
                }
            }
            val settings = binding.webView.settings
            settings.javaScriptCanOpenWindowsAutomatically = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        val menuBookmark = menu!!.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookmark( detail )
            true
        }
        viewModel.isBookmark.observe(this, Observer{
            if (it == 0) menuBookmark.setIcon(R.drawable.ic_add)
            else menuBookmark.setIcon(R.drawable.ic_check)
        })

        return super.onCreateOptionsMenu(menu)
    }
}