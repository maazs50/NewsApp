package com.tarang.newsapp.domain.model

import com.tarang.newsapp.data.remote.dto.ArticleDto


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)
