package com.example.starterproject.data.crypto

import com.example.starterproject.data.crypto.model.Coin
import com.example.starterproject.data.crypto.model.CoinDetail
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinAPI
) {
    suspend fun getCoins(): Result<List<Coin>> {
        return handleApi {  api.getCoins() }
    }

    suspend fun getCoinDetails(coinId: String): Result<CoinDetail> {
        return handleApi {  api.getCoinDetails(coinId) }
    }

}