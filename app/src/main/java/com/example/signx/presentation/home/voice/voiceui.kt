package com.example.signx.presentation.home.voice

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signx.R


@Preview(showBackground=true)
@Composable
fun SpeakToSignScreen(onBackClick: () -> Unit = {}) {
    var showRecorder by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
        ) {
            // Header with Back Arrow
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF121417)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Speak to Translate into Sign",
                        color = Color(0xFF121417),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 23.sp
                    )
                }

                // Mic Icon on the right
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF000000)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_24),
                        contentDescription = "Mic",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Description
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Speak clearly and naturally into the microphone. The app will transcribe your speech in real-time and translate it into sign language.",
                    color = Color(0xFF121417),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            // Image with gradient overlay and caption
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(270.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Sign Language",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.4f),
                                    Color.Transparent
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        "Real-time Transcription",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "The app will transcribe your speech in real-time and translate it into sign language.",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    )
                }
            }

            // Main action button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .background(Color(0xFF1A66E5), shape = RoundedCornerShape(28.dp))
                        .padding(start = 16.dp, end = 24.dp)
                        .clickable {
                            showRecorder = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mic_24),
                        contentDescription = "Mic",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Show Recorder if flag is true
        if (showRecorder) {
            VoiceRecorderUI(onDismiss = { showRecorder = false })
        }

        // Floating "Translate to Sign" button
        Box(
            modifier = Modifier
                .absoluteOffset(x = 118.dp, y = 562.dp)
                .width(160.dp)
                .height(40.dp)
                .background(Color(0xFF1A66E5), shape = RoundedCornerShape(20.dp))
                .clickable {},
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Translate to Sign",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
        }

        // Floating "Clear" button
        Box(
            modifier = Modifier
                .absoluteOffset(x = 159.dp, y = 630.dp)
                .width(84.dp)
                .height(40.dp)
                .background(Color(0xFFF0F2F5), shape = RoundedCornerShape(20.dp))
                .clickable { /* TODO: Clear action */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Clear",
                color = Color(0xFF121417),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
fun VoiceRecorderUI(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val audioRecorder = remember { AudioRecorder(context) }
    var isRecording by remember { mutableStateOf(false) }
    val permissionGranted = rememberAudioPermission()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)) // semi-transparent background
            .clickable { onDismiss() }, // dismiss on outside click
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (permissionGranted) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            if (isRecording) Color.Red else Color(0xFF1A66E5),
                            shape = CircleShape
                        )
                        .clickable {
                            if (isRecording) {
                                audioRecorder.stopRecording()
                            } else {
                                audioRecorder.startRecording()
                            }
                            isRecording = !isRecording
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mic_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    if (isRecording) "Recording..." else "Tap to Record",
                    color = if (isRecording) Color.Red else Color.Black
                )
            } else {
                Text("Microphone permission required.")
            }

            Spacer(Modifier.height(16.dp))
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    }
}
