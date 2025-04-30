package com.example.signx.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signx.presentation.auth.AuthViewModel
import com.example.signx.presentation.auth.LoginScreenWithViewModel
import com.example.signx.presentation.auth.RegisterScreenWithViewModel
import com.example.signx.presentation.auth.HomeScreen  // ✅ Import correct HomeScreen!

@Composable
fun SignTranslatorApp(
    viewModel: AuthViewModel = viewModel(),
    launchGoogleSignIn: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // Login Screen
        composable("login") {
            LoginScreenWithViewModel(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }  // ✅ This clears login from backstack
                    }
                },
                launchGoogleSignIn = launchGoogleSignIn,
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        // Register Screen
        composable("register") {
            RegisterScreenWithViewModel(
                viewModel = viewModel,
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }  // ✅ Clear register from backstack
                    }
                },
                launchGoogleSignIn = launchGoogleSignIn,
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen()
        }
    }
}
