package com.robby.news

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.robby.news.source.network.networkModule
import com.robby.news.source.news.repositoryModule
import com.robby.news.source.persistence.databaseModule
import com.robby.news.ui.bookmark.bookmarkModule
import com.robby.news.ui.bookmark.bookmarkViewModel
import com.robby.news.ui.detail.detailModule
import com.robby.news.ui.detail.detailViewModel
import com.robby.news.ui.home.homeModule
import com.robby.news.ui.home.homeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                homeModule,
                homeViewModel,
                bookmarkModule,
                bookmarkViewModel,
                detailModule,
                detailViewModel
            )
        }
    }
}