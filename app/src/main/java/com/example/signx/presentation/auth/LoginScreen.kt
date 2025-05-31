package com.example.signx.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signx.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    uiState: AuthUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    launchGoogleSignIn: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    if (uiState.isLoggedIn) {
        onLoginSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Bottom "Don’t Have Account? Register"
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(
                "Don’t Have Account? Register",
                color = Color(0xFF6B7382),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToRegister() }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(393.dp)
                .absoluteOffset(y = 91.dp)
                .padding(top = 60.dp)
            , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            // Header
            Text(
                "Sign In",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF121417),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )

            // Email Input
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                placeholder = { Text("Email", color = Color(0xFF61788A)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF0F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Password Input
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                placeholder = { Text("Password", color = Color(0xFF61788A)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF0F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D63F2)
                )
            ) {
                Text("Continue with Email", fontWeight = FontWeight.Bold)
            }

            // Separator
            Text(
                "or",
                color = Color(0xFF61788A),
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center
            )

            // Google Sign-In
            OutlinedButton(
                onClick = launchGoogleSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFF0F2F5)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24), // Add your Google icon
                    contentDescription = "Google",
                    tint = Color.Unspecified
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Sign in with Google",
                    color = Color(0xFF121417),
                    fontWeight = FontWeight.Bold
                )
            }

            // Error Message
            if (uiState.authMessage.isNotEmpty()) {
                Text(
                    uiState.authMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Forgot Password
        Text(
            "Forgot Password?",
            color = Color(0xFF121417),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-100).dp)
                .clickable { /* Handle forgot password */ }
        )
    }
}
@Composable
fun LoginScreenWithViewModel(
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onLoginSuccess: () -> Unit,
    launchGoogleSignIn: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LoginScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::login,
        onLoginSuccess = onLoginSuccess,
        launchGoogleSignIn = launchGoogleSignIn,
        onNavigateToRegister = onNavigateToRegister
    )
}
// Keep your existing Preview and WithViewModel functions unchanged

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        uiState = AuthUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onLoginSuccess = {},
        launchGoogleSignIn = {},
        onNavigateToRegister = {} // Preview
    )
}













@Preview(showBackground = true)
@Composable
fun SignInScreen() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .heightIn(min = 844.dp)
            .background(Color.White)
    ) {
        // "Don’t Have Account? Register" at the bottom
        Column(
            modifier = Modifier
                .width(393.dp)
                .absoluteOffset(y = 626.dp)
                .align(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Don’t Have Account? Register",
                    color = Color(0xFF6B7382),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    lineHeight = 21.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // "Forgot Password?" button
        Box(
            modifier = Modifier
                .width(393.dp)
                .absoluteOffset(y = 562.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Forgot Password?",
                    color = Color(0xFF121417),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 21.sp
                )
            }
        }

        // Main content
        Column(
            modifier = Modifier
                .width(393.dp)
                .absoluteOffset(y = 91.dp)
        ) {
            Spacer(modifier = Modifier.height(66.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Sign In",
                    color = Color(0xFF121417),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 23.sp,
                    modifier = Modifier
                        .padding(horizontal = 48.dp)
                        .fillMaxWidth()
                )
            }

            // Email input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp)
                    .background(Color(0xFFF0F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Email",
                    color = Color(0xFF61788A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }

            // Password input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp)
                    .background(Color(0xFFF0F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Password",
                    color = Color(0xFF61788A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }

            // Continue with Email button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF0D63F2), shape = RoundedCornerShape(24.dp))
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Continue with Email",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }

            // "or" separator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "or",
                    color = Color(0xFF61788A),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    lineHeight = 21.sp
                )
            }

            // Sign in with Google button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF0F2F5), shape = RoundedCornerShape(24.dp))
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Sign in with Google",
                        color = Color(0xFF121417),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}
