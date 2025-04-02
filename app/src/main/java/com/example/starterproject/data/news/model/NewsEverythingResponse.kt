package com.example.starterproject.data.news.model

data class NewsEverythingResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)