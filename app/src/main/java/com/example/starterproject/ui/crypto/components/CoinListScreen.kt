package com.example.starterproject.ui.crypto.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.starterproject.ui.crypto.navigation.CoinDetails
import com.example.starterproject.ui.crypto.viewModel.CoinViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter


@OptIn(FlowPreview::class)
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CoinListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: CoinViewModel = hiltViewModel()

    val coins by viewModel.coinList.collectAsState()

    val query =  remember { MutableStateFlow("") }
    val query1 =  remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        query.filter { it.isNotBlank()

        }
    }



    LazyColumn(
        modifier = modifier.navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(coins) { coin ->
            OutlinedButton(onClick = {
                navController.navigate(CoinDetails(coin.id))
            }) {
                Text("${coin.name} (${coin.id})")

            }
        }
    }
}