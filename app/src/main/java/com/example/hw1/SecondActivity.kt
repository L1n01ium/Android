package com.example.hw1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_FULL_TEXT = "extra_full_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fullText = intent.getStringExtra(EXTRA_FULL_TEXT) ?: ""

        setContent {
            MaterialTheme {
                SecondScreen(fullText)
            }
        }
    }

    @Composable
    fun SecondScreen(text: String) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text, style = MaterialTheme.typography.headlineMedium)
        }
    }
}