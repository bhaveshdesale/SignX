package com.example.signx.presentation.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    // Called when name text field changes
    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName) }
    }

    // Called when email text field changes
    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    // Called when password text field changes
    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    // Called when re-enter password text field changes
    fun onReEnterPasswordChange(newPassword: String) {
        _uiState.update { it.copy(reEnterPassword = newPassword) }
    }

    // Normal Email/Password Login
    fun login() {
        val email = uiState.value.email
        val password = uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(authMessage = "Please enter email and password.") }
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _uiState.update { it.copy(isLoggedIn = true, authMessage = "Login Successful!") }
            } else {
                _uiState.update { it.copy(authMessage = task.exception?.message ?: "Login failed") }
            }
        }
    }

    // Email/Password Register with saving name to Firestore
    fun register() {
        val name = uiState.value.name
        val email = uiState.value.email
        val password = uiState.value.password
        val reEnterPassword = uiState.value.reEnterPassword

        if (reEnterPassword != null) {
            if (name.isBlank() || email.isBlank() || password.isBlank() || reEnterPassword.isBlank()) {
                _uiState.update { it.copy(authMessage = "Please enter name, email, and both password fields.") }
                return
            }
        }

        if (password != reEnterPassword) {
            _uiState.update { it.copy(authMessage = "Passwords do not match.") }
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                saveUserToFirestore(userId, name, email)

                _uiState.update { it.copy(isLoggedIn = true, authMessage = "Registration Successful!") }
            } else {
                _uiState.update { it.copy(authMessage = task.exception?.message ?: "Registration failed") }
            }
        }
    }

    private fun saveUserToFirestore(userId: String, name: String, email: String) {
        val user = hashMapOf(
            "uid" to userId,
            "name" to name,
            "email" to email
        )

        firestore.collection("users").document(userId).set(user)
            .addOnSuccessListener {
                // User data saved successfully
            }
            .addOnFailureListener {
                // Failed to save user data
            }
    }

    // Google Sign-In
    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    saveUserToFirestore(it.uid, it.displayName ?: "No Name", it.email ?: "")
                }
                _uiState.update { it.copy(isLoggedIn = true, authMessage = "Google Sign-In Successful!") }
            } else {
                _uiState.update { it.copy(authMessage = task.exception?.message ?: "Google Sign-In failed") }
            }
        }
    }
}
