package com.example.trendora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trendora.ui.home.view.ProductListScreen
import com.example.trendora.ui.theme.TrendoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrendoraTheme {
                ProductListScreen()
            }
        }
    }
}
