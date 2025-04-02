package com.example.starterproject.ui.crypto.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
class CoinDetails (val symbol: String)

@Serializable
class NewsDetails (val url: String)
