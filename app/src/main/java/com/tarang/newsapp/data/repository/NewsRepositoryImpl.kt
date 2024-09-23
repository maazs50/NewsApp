package com.tarang.newsapp.data.repository

import com.tarang.newsapp.data.remote.NewsApi
import com.tarang.newsapp.data.remote.dto.NewsResponseDto
import com.tarang.newsapp.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getNews(): Response<NewsResponseDto> {
        return api.getBreakingNews()
    }

    override suspend fun getSearchedNews(query: String): Response<NewsResponseDto> {
        return api.searchForNews(query)
    }

}