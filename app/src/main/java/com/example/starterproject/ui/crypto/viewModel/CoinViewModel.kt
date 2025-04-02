package com.example.starterproject.ui.crypto.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starterproject.data.crypto.CoinRepository
import com.example.starterproject.data.crypto.Result
import com.example.starterproject.data.crypto.model.Coin
import com.example.starterproject.data.crypto.model.CoinDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("TimberArgCount")
class CoinViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {
    val _coinList = MutableStateFlow<List<Coin>>(emptyList())
    val coinList = _coinList.asStateFlow()

    val _coinDetails = MutableStateFlow<CoinDetail?>(null)
    val coinDetails = _coinDetails.asStateFlow()

    val _error = MutableStateFlow<String>("")
    val error = _error.asStateFlow()

    init {
        Log.i("CoinViewModel", "Fetching coins...")
        viewModelScope.launch {
            getCoins()
            Log.i("CoinViewModel", "Coins List is: ${coinList.value}")
        }
    }


    suspend fun getCoins() {
        val result = repository.getCoins()
        when (result) {
            is Result.Error -> {
                _error.update { result.message ?: "" }
            }

            is Result.Exception -> {
                _error.update { result.e.message ?: "" }
            }

            is Result.Success -> {
                _coinList.update { result.data }
            }
        }
    }

    suspend fun getCoinDetails(coinId: String) {
        val result = repository.getCoinDetails(coinId = coinId)
        when (result) {
            is Result.Error -> {
                _error.update { result.message ?: "" }
            }

            is Result.Exception -> {
                _error.update { result.e.message ?: "" }
            }

            is Result.Success -> {
                _coinDetails.update { result.data }
                Log.i("CoinViewModel", "Coins Details for coinId is: ${coinDetails.value}")
            }
        }
    }
}