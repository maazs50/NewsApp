package com.tarang.newsapp.di

import com.tarang.newsapp.common.Constants
import com.tarang.newsapp.data.remote.NewsApi
import com.tarang.newsapp.data.repository.NewsRepositoryImpl
import com.tarang.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    /*
    * @api uses the above provides api function
    * */
    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository{
        //We can change different impl classes that use NewsRepository for testing
        return NewsRepositoryImpl(api)
    }


}