package com.example.hw1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hw1.data.ItemRepository
import com.example.hw1.ui.theme.DetailScreen
import com.example.hw1.ui.theme.ListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            ListScreen(navController)
                        }
                        composable("detail/{itemId}") { backStackEntry ->
                            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
                            val item = ItemRepository.items.firstOrNull { it.id == itemId }
                            if (item != null) {
                                DetailScreen(navController, item)
                            }
                        }
                    }
                }
            }
        }
    }
}