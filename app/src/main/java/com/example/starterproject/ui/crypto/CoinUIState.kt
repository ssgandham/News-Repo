package com.example.starterproject.ui.crypto

import com.example.starterproject.data.crypto.model.Coin

data class CoinUIState (
    val coinList: List<Coin> = emptyList()

)
