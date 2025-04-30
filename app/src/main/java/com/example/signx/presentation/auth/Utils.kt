package com.example.signx.presentation.auth

import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
    )
    return emailPattern.matcher(email).matches()
}
fun isStrongPassword(password: String): Boolean {
    if (password.length < 6) return false
    val uppercase = password.any { it.isUpperCase() }
    val lowercase = password.any { it.isLowerCase() }
    val digit = password.any { it.isDigit() }
    val special = password.any { !it.isLetterOrDigit() }
    return uppercase && lowercase && digit && special
}

