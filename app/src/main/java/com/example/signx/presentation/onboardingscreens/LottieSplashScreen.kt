package com.example.signx.presentation.onboardingscreens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.signx.R

@Composable
fun LottieSplashScreen(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit
) {
    // Load the Lottie composition
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))

    // Play the animation state
    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,        // play once
        speed = 1.0f,
        restartOnPlay = false,
        isPlaying = true,
    )

    // Detect when animation finishes
    LaunchedEffect(animationState.isAtEnd && !animationState.isPlaying) {
        if (animationState.isAtEnd && !animationState.isPlaying) {
            onAnimationFinished()
        }
    }

    // Show Lottie animation
    LottieAnimation(
        composition = composition,
        progress = { animationState.progress },
        modifier = modifier
    )
}
