package com.tarang.newsapp.domain.repository

import com.tarang.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(): Response<NewsResponseDto>
    suspend fun getSearchedNews(query: String): Response<NewsResponseDto>
}