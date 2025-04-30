package com.example.signx.presentation.auth

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val authMessage: String = ""
)
