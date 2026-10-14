package com.example.hw1

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    val generatedPassword: String = generatePassword()

    private fun generatePassword(): String {
        val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val digits = "0123456789"

        val chars = mutableListOf<Char>()
        repeat(2) { chars += letters.random() }
        repeat(2) { chars += digits.random() }
        repeat(6) { chars += (letters + digits).random() }

        return chars.shuffled().joinToString("")
    }
}