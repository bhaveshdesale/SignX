package com.example.signx.presentation.home.voice

import android.Manifest
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

@Composable
fun rememberAudioPermission(): Boolean {
    val context = LocalContext.current
    var granted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        granted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
        if (!granted) {
            ActivityCompat.requestPermissions(
                context as android.app.Activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                0
            )
        }
    }
    return granted
}
