package com.example.starterproject.data.news


import com.example.starterproject.data.news.model.NewsEverythingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/everything")
    suspend fun getEverything(@Query("q") q: String, @Query("apiKey") apiKey: String): Response<NewsEverythingResponse>
}
