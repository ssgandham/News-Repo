package com.example.starterproject.ui.news.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.starterproject.ui.crypto.navigation.NewsDetails
import com.example.starterproject.ui.news.NewsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewsScreen (modifier: Modifier, navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    val newsViewModel: NewsViewModel = hiltViewModel()

    val results by newsViewModel.allArticles.collectAsState()

    LaunchedEffect(query) {
            newsViewModel.getEverything(query)

    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            OutlinedTextField(
                shape = CircleShape,
                maxLines = 1,
                modifier = modifier.padding(16.dp),
                value = query,
                onValueChange = {
                    query = it
                }
            )
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(results.articles) { result ->

                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(1.dp),
                    onClick = {
                        navController.navigate(NewsDetails(result.url))
                    }
                ) {

                    Row (modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            modifier = Modifier.size(150.dp).padding(4.dp),
                            model = result.urlToImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(result.title)
                            Text(result.source.name)
                        }
                    }
                }

            }
        }
    }



}
