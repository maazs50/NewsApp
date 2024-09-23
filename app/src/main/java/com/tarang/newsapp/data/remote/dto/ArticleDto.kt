package com.tarang.newsapp.data.remote.dto

import com.tarang.newsapp.domain.model.Article
import com.tarang.newsapp.domain.model.Source

//Full data collected for api call
data class ArticleDto(
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

//We don't need the author field for further use
fun ArticleDto.toArticle() : Article {
    return Article(
        id = id,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}