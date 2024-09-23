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

class GetBreakingNewsListUseCase @Inject constructor(
    private val repository: NewsRepository
){
    /*
    * This is used for Response we get from api
    * Extra data with list of articles
    * */
    operator fun invoke(): Flow<Resource<NewsResponse>> = flow {
        try{
            emit(Resource.Loading())
            val news = repository.getNews().body()?.toNewsResponse()
            emit(Resource.Success(news!!))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server, check internet connection"))
        }
    }

    /*
    * This is used for just list of articles
    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        try{
            emit(Resource.Loading())
            val news = repository.getNews().body()?.articles?.map { it.toArticle() }
            emit(Resource.Success(news!!))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server, check internet connection"))
        }
    }*/
}