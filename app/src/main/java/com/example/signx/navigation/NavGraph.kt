//package com.example.signx.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.signx.presentation.home.learn.LearnSignsScreen
//import com.example.signx.presentation.auth.AuthViewModel
//import com.example.signx.presentation.auth.LoginScreenWithViewModel
//import com.example.signx.presentation.auth.RegisterScreenWithViewModel
//import com.example.signx.presentation.home.HomeScreen1
//import com.example.signx.presentation.home.camera.SpeechToSignScreen
//import com.example.signx.presentation.home.voice.SpeakToSignScreen
//
//@Composable
//fun SignTranslatorApp(
//    viewModel: AuthViewModel = viewModel(),
//    launchGoogleSignIn: () -> Unit
//) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//
//        // Login Screen
//        composable("login") {
//            LoginScreenWithViewModel(
//                viewModel = viewModel,
//                onLoginSuccess = {
//                    navController.navigate("home") {
//                        popUpTo("login") { inclusive = true }
//                    }
//                },
//                launchGoogleSignIn = launchGoogleSignIn,
//                onNavigateToRegister = { navController.navigate("register") }
//            )
//        }
//
//        // Register Screen
//        composable("register") {
//            RegisterScreenWithViewModel(
//                viewModel = viewModel,
//                onRegisterSuccess = {
//                    navController.navigate("home") {
//                        popUpTo("register") { inclusive = true }
//                    }
//                },
//                launchGoogleSignIn = launchGoogleSignIn,
//                onNavigateToLogin = { navController.navigate("login") }
//            )
//        }
//
//        // Home Screen
//        composable("home") {
//            HomeScreen1()
//        }
//        composable("home") { HomeScreen1(
//            onStartTranslation = {navController.navigate("camera")},
//            onCameraClick = { navController.navigate("camera") },
//            onVoiceClick = { navController.navigate("voice")},
//            onLearnClick = { navController.navigate("Learn")}
//            // ...other lambdas
//        ) }
//        composable("camera") { SpeechToSignScreen(onBackClick = { navController.popBackStack() }) }
//        composable("voice"){SpeakToSignScreen(onBackClick = {navController.popBackStack()})}
//        composable("Learn") { LearnSignsScreen(onBackClick = {navController.popBackStack()}) }
//        // Add other screens as needed
//
//    }
//
//}
//


package com.example.signx.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signx.presentation.home.learn.LearnSignsScreen
import com.example.signx.presentation.auth.AuthViewModel
import com.example.signx.presentation.auth.LoginScreenWithViewModel
import com.example.signx.presentation.auth.RegisterScreenWithViewModel
import com.example.signx.presentation.home.HomeScreen1
import com.example.signx.presentation.home.camera.SpeechToSignScreen
import com.example.signx.presentation.home.voice.SpeakToSignScreen
import com.example.signx.presentation.onboardingscreens.OnboardingPager
import com.example.signx.presentation.onboardingscreens.onboardingPages
import com.example.signx.presentation.onboardingscreens.LottieSplashScreen
import androidx.compose.ui.Modifier
import com.example.signx.presentation.home.profile.CompletedProfileScreen
import com.example.signx.presentation.home.profile.ProfileScreen1
import com.example.signx.presentation.home.settings.SettingsScreen

@Composable
fun SignTranslatorApp(
    viewModel: AuthViewModel = viewModel(),
    launchGoogleSignIn: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        // Splash Screen
        composable("splash") {
            LottieSplashScreen(
                modifier = Modifier.fillMaxSize(),
                onAnimationFinished = {
                    navController.navigate("onboarding") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // Onboarding Screen using your existing OnboardingPager composable
        composable("onboarding") {
            OnboardingPager(
                pages = onboardingPages,
                onFinish = {
                    navController.navigate("login") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreenWithViewModel(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
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
                        popUpTo("register") { inclusive = true }
                    }
                },
                launchGoogleSignIn = launchGoogleSignIn,
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen1(
                onStartTranslation = { navController.navigate("camera") },
                onCameraClick = { navController.navigate("camera") },
                onVoiceClick = { navController.navigate("voice") },
                onLearnClick = { navController.navigate("Learn") },
                onProfileClick = { navController.navigate("profile") },
                onSettingClick = { navController.navigate("settings") }

            )
        }

        composable("camera") {
            SpeechToSignScreen(onBackClick = { navController.popBackStack() })
        }
        composable("voice") {
            SpeakToSignScreen(onBackClick = { navController.popBackStack() })
        }
        composable("Learn") {
            LearnSignsScreen(onBackClick = { navController.popBackStack() })
        }
        composable("profile") {
            CompletedProfileScreen(
                onBackClick = { navController.popBackStack() },
                        onEditProfileClick = { navController.navigate("editProfile") }
            )
        }
        composable("editProfile") {
            ProfileScreen1(
                onBackClick = { navController.popBackStack() },
                onSaveChanges = {
                    // Handle save changes logic here
                    navController.popBackStack() // Go back to profile screen after saving
                }
            )
        }
        composable("settings") {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onLanguageClick = { /* Handle language selection */ },
                onThemeClick = { /* Handle theme selection */ },
                onNotificationsClick = { /* Handle notifications */ },
                onPrivacyPolicyClick = { /* Handle privacy policy */ },
                onHelpSupportClick = { /* Handle help & support */ },
                onLogoutClick = {
                    // Handle logout logic
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }


    }
}
