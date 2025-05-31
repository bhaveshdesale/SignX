package com.example.signx.presentation.onboardingscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signx.R

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val buttonText: String
)

val onboardingPages = listOf(
    OnboardingPage(
        imageRes = R.drawable.one,
        title = "Instant Recognition with AI",
        buttonText = "Next"
    ),
    OnboardingPage(
        imageRes = R.drawable.two,
        title = "Translate Sign Language in Real Time",
        buttonText = "Next"
    ),
    OnboardingPage(
        imageRes = R.drawable.three,
        title = "Bridging the Communication Gap",
        buttonText = "Next"
    ),
)

@Composable
fun OnboardingPager(
    pages: List<OnboardingPage>,
    onFinish: () -> Unit = {}
) {
    var currentPage by remember { mutableStateOf(0) }

    OnboardingScreen(
        page = pages[currentPage],
        onButtonClick = {
            if (currentPage < pages.lastIndex) {
                currentPage++
            } else {
                onFinish()
            }
        }
    )
}

@Composable
fun OnboardingScreen(
    page: OnboardingPage,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 25.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = page.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = page.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121417),
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D63F2)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = page.buttonText,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPagerPreview() {
    OnboardingPager(
        pages = onboardingPages,
        onFinish = { /* Navigate to next screen */ }
    )
}
