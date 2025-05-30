package com.example.signx.presentation.onboardingScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.signx.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerScreen() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 4 }
    )

    val pages = listOf(
        OnboardingPage(
            title = "SignBridge",
            subtitle = "Real-Time Sign Language Translator",
            tagline = "Breaking Barriers with Hands & AI",
            imageRes = R.drawable.one
        ),
        OnboardingPage(
            title = "Smart Communication",
            subtitle = "AI-Powered Translation",
            tagline = "Understand sign language easily",
            imageRes = R.drawable.two
        ),
        OnboardingPage(
            title = "Fast & Efficient",
            subtitle = "Instant Voice-To-Sign",
            tagline = "Bridge gaps in real-time",
            imageRes = R.drawable.three
        ),
        OnboardingPage(
            title = "Empower All",
            subtitle = "Accessibility Matters",
            tagline = "Connect. Communicate. Care.",
            imageRes = R.drawable.three // Changed to R.drawable.four to avoid duplication (if available)
        )
    )

    HorizontalPager(
        pageSize = PageSize.Fill,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        OnboardingScreen(
            currentPage = page,
            totalPages = pages.size,
            imageRes = pages[page].imageRes,
            title = pages[page].title,
            subtitle = pages[page].subtitle,
            tagline = pages[page].tagline
        )
    }
}

data class OnboardingPage(
    val title: String,
    val subtitle: String,
    val tagline: String,
    val imageRes: Int
)
