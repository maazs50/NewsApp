package com.tarang.newsapp.presentation.news_list

import com.tarang.newsapp.domain.model.Article
import com.tarang.newsapp.domain.model.NewsResponse
import retrofit2.Response

data class NewsListState(
    val isLoading: Boolean = false,
    val newsResponse: NewsResponse? = null,
    val error: String = ""
)