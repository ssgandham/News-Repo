package com.example.starterproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.starterproject.ui.crypto.navigation.HomeScreen
import com.example.starterproject.ui.crypto.navigation.NewsDetails
import com.example.starterproject.ui.crypto.theme.StarterProjectTheme
import com.example.starterproject.ui.news.NewsViewModel
import com.example.starterproject.ui.news.components.NewsDetailsScreen
import com.example.starterproject.ui.news.components.NewsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity", "App started")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarterProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: NewsViewModel = hiltViewModel()
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = HomeScreen) {
                        composable<HomeScreen> {
                            NewsScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable<NewsDetails> { backStackEntry ->
                            val newsDetails: NewsDetails = backStackEntry.toRoute()
                            NewsDetailsScreen(
                                modifier = Modifier.padding(innerPadding), newsDetails.url,
                                context = this@MainActivity
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarterProjectTheme {
        Greeting("Android")
    }
}