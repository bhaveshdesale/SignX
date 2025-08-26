package com.example.signx.presentation.home
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.signx.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground=true)
@Composable
fun HomeScreen1(
    onStartTranslation: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onCameraClick: () -> Unit ={},
    onVoiceClick: () -> Unit = {},

    onLearnClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSettingClick:()->Unit={}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sign Language AI",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onProfileClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(32.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick =  onSettingClick) { // Change to use settings click handler
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = "Settings",
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFF0F2F5))
//                elevation = 4.dp
            )
        },
        bottomBar = {
            NavigationBar(
            containerColor =   Color(0xFFF0F2F5),
            tonalElevation = 4.dp
        )
            {
                BottomNavigationBar(
                    onHomeClick = onHomeClick,
                    onCameraClick = onCameraClick,
                    onVoiceClick = onVoiceClick,
                    onLearnClick = onLearnClick,
                    selectedItem = "Home"
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Language selection row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "American Sign Language",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF121417),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "dropdown Icon",
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // How it works title
                Text(
                    text = "How It Works",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121417)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Steps
                HowItWorksStep(
                    imageRes = R.drawable.one,
                    title = "Capture Sign",
                    description = "Capture sign language using your device's camera."
                )
                HowItWorksStep(
                    imageRes = R.drawable.two,
                    title = "Convert to Text",
                    description = "The app converts sign language into text in real-time."
                )
                HowItWorksStep(
                    imageRes = R.drawable.three,
                    title = "Speech-to-Sign",
                    description = "Translate spoken words into sign language."
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Start Translation Button
                Button(
                    onClick = onStartTranslation,
                    colors = ButtonDefaults.buttonColors(Color(0xFFB2C7E5)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(48.dp)
                        .width(200.dp)
                ) {
                    Text(
                        "Start Translation",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF121417)
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))
            }
        },
//        backgroundColor = Color.White
    )
}

@Composable
fun HowItWorksStep(
    imageRes: Int,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF121417)
            )
            Text(
                description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF6B7382)
            )
        }
    }
}
@Composable
fun BottomNavigationBar(
    selectedItem: String,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onLearnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background( Color(0xFFF0F2F5))
            .border(BorderStroke(0.dp, Color(0xFFF0F2F5))),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            label = "Home",
            iconRes = R.drawable.baseline_home_24,
            isSelected = selectedItem == "Home",
            onClick = onHomeClick
        )
        BottomNavItem(
            label = "Camera",
            iconRes = R.drawable.baseline_camera_alt_24,
            isSelected = selectedItem == "Camera",
            onClick = onCameraClick
        )
        BottomNavItem(
            label = "Voice",
            iconRes = R.drawable.baseline_mic_24,
            isSelected = selectedItem == "Voice",
            onClick = onVoiceClick
        )
        BottomNavItem(
            label = "Learn",
            iconRes = R.drawable.baseline_person_24,
            isSelected = selectedItem == "Learn",
            onClick = onLearnClick
        )

    }
}

@Composable
fun BottomNavItem(
    label: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconColor = if (isSelected) Color(0xFF121417) else Color(0xFF637087)

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = iconColor
        )
    }
}
