package com.example.signx.presentation.home.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signx.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Settings",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF121417)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back",
                            tint = Color(0xFF121417)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0F2F5),
                    titleContentColor = Color(0xFF121417)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            // Header section
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Preferences",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6B7382),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            // Preferences section
            SettingsItem(
                iconRes = R.drawable.baseline_sign_language_24,
                title = "Preferred Sign Language",
                onClick = onLanguageClick
            )

            SettingsItem(
                iconRes = R.drawable.baseline_settings_backup_restore_24,
                title = "App Theme",
                onClick = onThemeClick
            )

            SettingsItem(
                iconRes = R.drawable.baseline_notifications_24,
                title = "Notifications",
                onClick = onNotificationsClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Support section header
            Text(
                text = "Support & About",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6B7382),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            SettingsItem(
                iconRes = R.drawable.baseline_policy_24,
                title = "Privacy Policy",
                onClick = onPrivacyPolicyClick
            )

            SettingsItem(
                iconRes = R.drawable.baseline_help_24,
                title = "Help & Support",
                onClick = onHelpSupportClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Account section header
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6B7382),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            SettingsItem(
                iconRes = R.drawable.baseline_logout_24,
                title = "Log out",
                onClick = onLogoutClick,
                showDivider = false,
                tint = Color(0xFFE53935) // Red color for logout
            )

            Spacer(modifier = Modifier.height(32.dp))

            // App version info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign Language AI",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7382),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Version 1.0.0",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF9AA0A6)
                )
            }
        }
    }
}

@Composable
fun SettingsItem(
    iconRes: Int,
    title: String,
    onClick: () -> Unit,
    showDivider: Boolean = true,
    tint: Color = Color(0xFF121417)
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
//                    indication = null // Remove ripple effect for cleaner look
                )
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = tint
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = tint,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = "Navigate",
                modifier = Modifier.size(20.dp),
                tint = Color(0xFF9AA0A6)
            )
        }

        if (showDivider) {
            Divider(
                modifier = Modifier.padding(start = 68.dp, end = 24.dp),
                thickness = 0.8.dp,
                color = Color(0xFFF0F2F5)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen()
    }
}