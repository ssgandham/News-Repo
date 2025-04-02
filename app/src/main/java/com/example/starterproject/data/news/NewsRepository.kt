package com.example.starterproject.data.news

import com.example.starterproject.data.news.model.NewsEverythingResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsAPI
) {
    suspend fun getEverything(query: String): Result<NewsEverythingResponse> {
        return handleApi { api.getEverything(q = query, apiKey = "9b97ef01e8f74bb39c7be7799d534160") }
    }

}