package com.example.starterproject.data.news.model

data class Article(
    val author: Any,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)