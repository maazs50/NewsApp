package com.tarang.newsapp.domain.use_case.get_news_list

import com.tarang.newsapp.common.Resource
import com.tarang.newsapp.data.remote.dto.toNewsResponse
import com.tarang.newsapp.domain.model.NewsResponse
import com.tarang.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetSearchedNewsListUseCase @Inject constructor(
    val repository: NewsRepository
) {
    operator fun invoke(query: String): Flow<Resource<NewsResponse>>
    {
        return flow {
            try {
                emit(Resource.Loading())
                val searchNews = repository.getSearchedNews(query).body()?.toNewsResponse()
                searchNews.let {
                    emit(Resource.Success(it!!))
                }
            } catch (e : HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException){
                emit(Resource.Error("Check internet connection"))
            }

        }
    }
}