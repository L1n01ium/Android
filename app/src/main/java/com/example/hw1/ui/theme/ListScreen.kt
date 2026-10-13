package com.example.hw1.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hw1.data.ItemRepository

@Composable
fun ListScreen(navController: NavController) {

    val items = ItemRepository.items

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                        navController.navigate("detail/${item.id}")
                    }
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}