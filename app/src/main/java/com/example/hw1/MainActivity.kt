package com.example.hw1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("result/{password}") { backStackEntry ->
                            val enteredPassword =
                                backStackEntry.arguments?.getString("password") ?: ""
                            ResultScreen(
                                navController = navController,
                                enteredPassword = enteredPassword,
                                generatedPassword = authViewModel.generatedPassword
                            )
                        }
                    }
                }
            }
        }
    }
}