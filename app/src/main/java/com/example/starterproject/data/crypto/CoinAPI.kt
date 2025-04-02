package com.example.starterproject.data.crypto

import com.example.starterproject.data.crypto.model.Coin
import com.example.starterproject.data.crypto.model.CoinDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinAPI {

    @GET("/v1/coins")
    suspend fun getCoins(): Response<List<Coin>>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): Response<CoinDetail>
}
