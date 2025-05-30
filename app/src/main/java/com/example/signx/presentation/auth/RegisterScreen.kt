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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RegisterScreen(
//    uiState: AuthUiState,
//    onNameChange: (String) -> Unit,
//    onEmailChange: (String) -> Unit,
//    onPasswordChange: (String) -> Unit,
//    onRegisterClick: () -> Unit,
//    onRegisterSuccess: () -> Unit,
//    launchGoogleSignIn: () -> Unit,
//    onNavigateToLogin: () -> Unit
//) {
//    // Auto navigate when register success
//    if (uiState.isLoggedIn) {
//        onRegisterSuccess()
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Register", fontSize = 28.sp, fontWeight = FontWeight.Bold)
//        Spacer(Modifier.height(24.dp))
//
//        OutlinedTextField(
//            value = uiState.name, // New name field
//            onValueChange = onNameChange,
//            label = { Text("Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = uiState.email,
//            onValueChange = onEmailChange,
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = uiState.password,
//            onValueChange = onPasswordChange,
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(Modifier.height(24.dp))
//        Button(
//            onClick = onRegisterClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Register")
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        // Google Sign In Button
//        OutlinedButton(
//            onClick = launchGoogleSignIn,
//            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_account_circle_24),
//                contentDescription = "Google Sign-In",
//                tint = Color.Unspecified
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Continue with Google", color = Color.Black)
//        }
//
//        // ✅ Already have an account? Login link
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(
//            text = "Already have an account? Login",
//            color = MaterialTheme.colorScheme.primary,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .clickable { onNavigateToLogin() }
//                .padding(8.dp)
//        )
//
//        // Show auth message if any (error or success message)
//        if (uiState.authMessage.isNotEmpty()) {
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(uiState.authMessage, color = Color.Red)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun RegisterScreenPreview() {
//    RegisterScreen(
//        uiState = AuthUiState(),
//        onNameChange = {},
//        onEmailChange = {},
//        onPasswordChange = {},
//        onRegisterClick = {},
//        onRegisterSuccess = {},
//        launchGoogleSignIn = {},
//        onNavigateToLogin = {}
//    )
//}



//@Composable
//fun RegisterScreenWithViewModel(
//    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
//    onRegisterSuccess: () -> Unit,
//    launchGoogleSignIn: () -> Unit,
//    onNavigateToLogin: () -> Unit
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    RegisterScreen(
//        uiState = uiState,
//        onNameChange = viewModel::onNameChange,
//        onEmailChange = viewModel::onEmailChange,
//        onPasswordChange = viewModel::onPasswordChange,
//        onRegisterClick = viewModel::register,
//        onRegisterSuccess = onRegisterSuccess,
//        launchGoogleSignIn = launchGoogleSignIn,
//        onNavigateToLogin = onNavigateToLogin
//    )
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    uiState: AuthUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onReEnterPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    launchGoogleSignIn: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    // Auto navigate when register success
    if (uiState.isLoggedIn) {
        onRegisterSuccess()
    }

    Box(
        modifier = Modifier
            .width(420.dp)
            .height(852.dp)
            .background(Color.White)
    ) {
        // "Already have an account? Login" link at the bottom
        Box(
            modifier = Modifier
                .absoluteOffset(x = 32.dp, y = 674.dp)
                .width(356.dp)
                .height(29.dp)
                .clickable { onNavigateToLogin() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Already have an account? Login",
                color = Color(0xFF6B7382),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
        }

        // Main content
        Column(
            modifier = Modifier
                .width(420.dp)
                .absoluteOffset(y = 109.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Sign Up",
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

            // Name input
            OutlinedTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp),
                placeholder = { Text("Name", color = Color(0xFF6B7382)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF2F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Email input
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp),
                placeholder = { Text("Email", color = Color(0xFF6B7382)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF2F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Password input
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp),
                placeholder = { Text("Password", color = Color(0xFF6B7382)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF2F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Re-enter Password input
            OutlinedTextField(
                value = uiState.reEnterPassword ?: "",
                onValueChange = onReEnterPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp),
                placeholder = { Text("Re-enter Password", color = Color(0xFF6B7382)) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFF2F2F5),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        // Register with Google Button
        Box(
            modifier = Modifier
                .absoluteOffset(x = 16.dp, y = 610.dp)
                .width(388.dp)
                .height(48.dp)
                .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                .clickable { launchGoogleSignIn() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Register with Google",
                color = Color(0xFF121417),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }

        // Continue with Email Button
        Box(
            modifier = Modifier
                .absoluteOffset(y = 485.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF0D63F2), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 20.dp)
                    .clickable { onRegisterClick() },
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
                .absoluteOffset(x = 0.dp, y = 557.dp)
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

        // Show auth message if any (error or success message)
        if (uiState.authMessage.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(y = 720.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.authMessage, color = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        uiState = AuthUiState(),
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onReEnterPasswordChange = {},
        onRegisterClick = {},
        onRegisterSuccess = {},
        launchGoogleSignIn = {},
        onNavigateToLogin = {}
    )
}

@Composable
fun RegisterScreenWithViewModel(
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onRegisterSuccess: () -> Unit,
    launchGoogleSignIn: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    RegisterScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onReEnterPasswordChange = viewModel::onReEnterPasswordChange, // Make sure your ViewModel supports this
        onRegisterClick = viewModel::register,
        onRegisterSuccess = onRegisterSuccess,
        launchGoogleSignIn = launchGoogleSignIn,
        onNavigateToLogin = onNavigateToLogin
    )
}


@Preview(showBackground = true)
@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit = {},
    onRegisterWithGoogle: () -> Unit = {},
    onContinueWithEmail: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(420.dp)
            .height(852.dp)
            .background(Color.White)
    ) {
        // "Already have an account? Login" at the bottom
        Box(
            modifier = Modifier
                .absoluteOffset(x = 32.dp, y = 674.dp)
                .width(356.dp)
                .height(29.dp)
                .clickable { onLoginClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Already have an account? Login",
                color = Color(0xFF6B7382),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
        }

        // Main content
        Column(
            modifier = Modifier
                .width(420.dp)
                .absoluteOffset(y = 109.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Sign Up",
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

            // Name input (replace with OutlinedTextField for functional input)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp)
                    .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Name",
                    color = Color(0xFF6B7382),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }

            // Email input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp)
                    .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Email",
                    color = Color(0xFF6B7382),
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
                    .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Password",
                    color = Color(0xFF6B7382),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }

            // Re-enter Password input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(56.dp)
                    .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "Re-enter Password",
                    color = Color(0xFF6B7382),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(72.dp))
        }

        // Register with Google Button
        Box(
            modifier = Modifier
                .absoluteOffset(x = 16.dp, y = 610.dp)
                .width(388.dp)
                .height(48.dp)
                .background(Color(0xFFF2F2F5), shape = RoundedCornerShape(12.dp))
                .clickable { onRegisterWithGoogle() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Register with Google",
                color = Color(0xFF121417),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }

        // Continue with Email Button
        Box(
            modifier = Modifier
                .absoluteOffset(y = 485.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF0D63F2), shape = RoundedCornerShape(24.dp))
                    .padding(horizontal = 20.dp)
                    .clickable { onContinueWithEmail() },
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
                .absoluteOffset(x = 0.dp, y = 557.dp)
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
    }
}



