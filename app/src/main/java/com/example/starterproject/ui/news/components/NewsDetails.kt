package com.example.starterproject.ui.news.components

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsDetailsScreen(modifier: Modifier = Modifier, url: String, context: Context) {
    AndroidView(factory = { WebView(context) }) { webView ->
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
}