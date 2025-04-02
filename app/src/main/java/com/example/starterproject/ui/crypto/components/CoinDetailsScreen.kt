package com.example.starterproject.ui.crypto.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starterproject.ui.crypto.viewModel.CoinViewModel


@Composable
fun CoinDetailsScreen(modifier: Modifier = Modifier, symbol: String) {


    val viewModel: CoinViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getCoinDetails(symbol)
    }

    val coinDetails by viewModel.coinDetails.collectAsState()



    Column {
        Text(coinDetails?.name.toString())
        Text(coinDetails?.symbol.toString())
        Text(coinDetails?.description.toString())
        Text(coinDetails?.tags.toString())
    }
}