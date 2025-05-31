package com.example.signx.presentation.onboardingscreens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.signx.R

@Composable
fun LottieSplashScreen(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1,
        speed = 1.0f,
        restartOnPlay = false,
        isPlaying = true,
    )

    // Detect when animation finishes
    LaunchedEffect(progress) {
        if (progress == 1f) {
            onAnimationFinished()
        }
    }

    LottieAnimation(
        composition,
        progress,
        modifier = modifier
    )
}
