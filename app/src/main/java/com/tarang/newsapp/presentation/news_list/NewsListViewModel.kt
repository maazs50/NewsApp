package com.tarang.newsapp.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarang.newsapp.common.Resource
import com.tarang.newsapp.domain.use_case.get_news_list.GetBreakingNewsListUseCase
import com.tarang.newsapp.domain.use_case.get_news_list.GetSearchedNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetBreakingNewsListUseCase,
    private val getSearchedNewsListUseCase: GetSearchedNewsListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getBreakingNews()
    }

    private fun getBreakingNews(){
        getNewsUseCase().onEach {  result ->
            when(result){
                is Resource.Success -> {
                    _state.value = NewsListState(newsResponse = result.data)
                }
                is Resource.Error ->{
                    _state.value = NewsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading ->{
                    _state.value = NewsListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSearchNews(query: String){
        getSearchedNewsListUseCase(query).onEach {  result ->
            when(result){
                is Resource.Success -> {
                    _state.value = NewsListState(newsResponse = result.data)
                }
                is Resource.Error ->{
                    _state.value = NewsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading ->{
                    _state.value = NewsListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
