package com.tarang.newsapp.data.remote

import com.tarang.newsapp.data.remote.dto.ArticleDto
import com.tarang.newsapp.data.remote.dto.toArticle
import com.tarang.newsapp.domain.model.Article
import com.tarang.newsapp.domain.model.NewsResponse

data class NewsResponseDto(
    val articles: MutableList<ArticleDto>,
    val status: String,
    val totalResults: Int
)

fun NewsResponseDto.toNewsResponse(): NewsResponse{
    return NewsResponse(
        articles = articles.map {
            it.toArticle()
        }.toMutableList(),
        status = status,
        totalResults = totalResults
    )
}