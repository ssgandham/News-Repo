package com.example.starterproject.ui.news

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starterproject.data.news.Result
import com.example.starterproject.data.news.NewsRepository
import com.example.starterproject.data.news.model.NewsEverythingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("TimberArgCount")
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    val _allArticles = MutableStateFlow<NewsEverythingResponse>(NewsEverythingResponse(emptyList(), "", 0))
    val allArticles = _allArticles.asStateFlow()

    val _error = MutableStateFlow<String>("")
    val error = _error.asStateFlow()

    init {
        Log.i("NewsViewModel", "Fetching news articles...")
        viewModelScope.launch {
            getEverything("USA")
            Log.i("NewsViewModel", "News results are : ${allArticles.value}")
        }
    }


    suspend fun getEverything(query: String) {
        val result = repository.getEverything(query = query)

        when (result) {
            is Result.Error -> {
                _error.update { result.message ?: "Unknown error" }
            }

            is Result.Exception -> {
                _error.update { result.e.message ?: "Exception occurred" }
            }

            is Result.Success -> {
                Log.i("NewsViewModel", "Articles fetched: ${result.data.articles.size}")
                _allArticles.update { result.data }
            }
        }
    }


}