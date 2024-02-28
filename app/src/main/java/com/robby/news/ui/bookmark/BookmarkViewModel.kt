package com.robby.news.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robby.news.source.news.NewsRepository
import org.koin.dsl.module

val bookmarkViewModel = module {
    factory { BookmarkViewModel(get()) }
}

class BookmarkViewModel(
    repository: NewsRepository
) : ViewModel() {

    val title = "Disimpan"
    val articles = repository.db.findAll()

}