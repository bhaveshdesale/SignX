package com.example.signx.presentation.onboardingscreens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.signx.presentation.onboardingscreens.LottieSplashScreen

@Composable
fun MainContent() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        LottieSplashScreen(
            onAnimationFinished = {
                showSplash = false
            }
        )
    } else {
        OnboardingPager(
            pages = onboardingPages,
            onFinish = {

            }
        )
    }
}
