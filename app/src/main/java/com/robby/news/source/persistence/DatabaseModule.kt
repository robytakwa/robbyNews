package com.robby.news.source.persistence

import android.app.Application
import androidx.room.Room
import com.robby.news.source.news.NewsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideArticle(get()) }
}

fun provideDatabase(application: Application): DatabaseClient {
    return Room.databaseBuilder(application, DatabaseClient::class.java, "lazdayNews.db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideArticle(database: DatabaseClient): NewsDao {
    return  database.newsDao
}

