package com.example.signx.presentation.home.learn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Preview(showBackground = true)
@Composable
fun LearnSignsScreenPreview() {
    LearnSignsScreen(onBackClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnSignsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Learn Signs",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF0F2F5))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            item {
                // Language Filters
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LanguageChip("ASL")
                    LanguageChip("ISL")
                    LanguageChip("BSL")
                }
            }

            items(signsList) { sign ->
                SignCard(
                    category = sign.category,
                    title = sign.title,
                    description = sign.description
                )
            }
        }
    }
}

@Composable
fun LanguageChip(label: String) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .background(Color(0xFFF0F2F5), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF121417)
        )
    }
}

@Composable
fun SignCard(category: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.width(228.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = category,
                color = Color(0xFF637087),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = title,
                color = Color(0xFF121417),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                color = Color(0xFF637087),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Image(
            painter = rememberAsyncImagePainter("https://placehold.co/130x91"),
            contentDescription = "$title sign image",
            modifier = Modifier
                .width(130.dp)
                .height(91.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

// Dummy data list
val signsList = listOf(
    Sign("Greetings", "Hello", "A common greeting used to start conversations."),
    Sign("Gratitude", "Thank You", "Expressing appreciation for a kind gesture or service."),
    Sign("Inquiry", "How Are You?", "A polite question to inquire about someone's well-being."),
    Sign("Feeling", "I'm Fine", "Expressing well-being or emotional state."),
    Sign("Time", "Good Morning", "Used to greet someone in the morning."),
    Sign("Time", "Good Night", "Used before sleeping."),
    Sign("Time", "Good Night", "Used before sleeping."),
    Sign("Time", "Good Night", "Used before sleeping."),

)

data class Sign(val category: String, val title: String, val description: String)
