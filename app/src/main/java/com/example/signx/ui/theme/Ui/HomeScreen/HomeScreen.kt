import androidx.compose.foundation.background

//package com.example.signx.ui.theme.Ui.HomeScreen
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.style.TextAlign
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AdaptiveUI()
//        }
//    }
//}
//
//@Composable
//fun AdaptiveUI() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFE6F0F2)),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Text(
//                text = "Tap to start talking",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                BottomNavItem(iconRes = android.R.drawable.ic_menu_myplaces)
//                BottomNavItem(iconRes = android.R.drawable.ic_menu_camera)
//
//                FloatingActionButton(
//                    onClick = { /* Handle Mic Click */ },
//                    modifier = Modifier.size(56.dp),
////                    containerColor = Color(0xFF4EC5C1)
//                            containerColor = Color(0xFF3B6FA1)
//                ) {
//                    Icon(
//                        painter = painterResource(id = android.R.drawable.ic_btn_speak_now),
//                        contentDescription = "Microphone",
//                        tint = Color.White
//                    )
//                }
//
//                BottomNavItem(iconRes = android.R.drawable.ic_menu_manage)
//                BottomNavItem(iconRes = android.R.drawable.ic_menu_myplaces)
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomNavItem(iconRes: Int) {
//    Icon(
//        painter = painterResource(id = iconRes),
//        contentDescription = null,
//        modifier = Modifier
//            .size(28.dp)
//            .clickable { }
//            .padding(4.dp),
//        tint = Color(0xFF4EC5C1)
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewAdaptiveUI() {
//    AdaptiveUI()
//}



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

// Define color palette
private val BackgroundColor = Color(0xFFD6DEE3) // Dark grayish-black
private val SurfaceColor = Color(0xFF1F1F1F)   // Dark surface
private val AccentColor = Color(0xFF4CAF50)    // Green accent (Material Green 500)
private val TextColor = Color.Black
private val InactiveIconColor = Color.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val currentScreen = remember { mutableStateOf(Screen.Home) }

    Scaffold(
        containerColor = BackgroundColor,
        bottomBar = {
            BottomAppBar(
                containerColor = SurfaceColor,
                contentColor = TextColor
            ) {
                BottomNavigation(currentScreen.value) { screen ->
                    currentScreen.value = screen
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundColor)
        ) {
            when (currentScreen.value) {
                Screen.Home -> HomeScreenContent()
                Screen.Profile -> ProfileScreen()
                Screen.Settings -> SettingsScreen()
            }
        }
    }
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Home",
                color = TextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Center voice control
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            VoiceControlSection()
        }
    }
}

@Composable
fun VoiceControlSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable { /* Handle voice recording */ },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Microphone",
            tint = AccentColor,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tap to Speak",
            color = TextColor,
            fontSize = 16.sp
        )
    }
}

@Composable
fun BottomNavigation(currentScreen: Screen, onItemSelected: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem(
            icon = Icons.Default.Home,
            isSelected = currentScreen == Screen.Home,
            onClick = { onItemSelected(Screen.Home) }
        )

        BottomNavItem(
            icon = Icons.Default.Person,
            isSelected = currentScreen == Screen.Profile,
            onClick = { onItemSelected(Screen.Profile) }
        )

        BottomNavItem(
            icon = Icons.Default.Settings,
            isSelected = currentScreen == Screen.Settings,
            onClick = { onItemSelected(Screen.Settings) }
        )
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    val tint = if (isSelected) AccentColor else InactiveIconColor

    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile Screen",
            color = TextColor,
            fontSize = 24.sp
        )
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings Screen",
            color = TextColor,
            fontSize = 24.sp
        )
    }
}

enum class Screen {
    Home, Profile, Settings
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}
