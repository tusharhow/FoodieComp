package com.example.foodiecomp.src.views.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.foodiecomp.src.components.CustomTextField
import com.example.foodiecomp.src.components.PrimaryButton
import com.example.foodiecomp.src.navigation.Screen
import com.example.foodiecomp.ui.theme.PrimaryColor
import com.example.foodiecomp.ui.theme.PrimaryLightColor

@Composable
fun LoginScreen(navHostController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Plain white background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Restaurant-themed Header Text
            Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(PrimaryLightColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            // Email TextField
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            // Password TextField
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            PrimaryButton(title = "Login", onClick = {
                navHostController.navigate(Screen.HomeScreen.route)
            })

            Spacer(modifier = Modifier.height(16.dp))

            // Forgot Password Text
            Text(
                text = "Forgot Password?",
                color = PrimaryColor,
                modifier = Modifier.clickable {
                    // Handle forgot password navigation
                },
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Link
            Text(
                text = "Don't have an account? Register",
                color = Color.Black,
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.RegisterScreen.route)
                },
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
