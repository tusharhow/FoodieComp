package com.example.foodiecomp.src.views.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiecomp.src.components.CustomTextField
import com.example.foodiecomp.src.components.PrimaryButton
import com.example.foodiecomp.src.components.SocialLoginButton
import com.example.foodiecomp.ui.theme.PrimaryColor
import com.example.foodiecomp.ui.theme.PrimaryLightColor


@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Plain white background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Restaurant-themed Header Text
            Text(
                text = "Join the Culinary Experience!",
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor, // Warm restaurant color (like a rich brown)
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(PrimaryLightColor) // Light beige background
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )

            // Username TextField
            CustomTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username"
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

            // Confirm Password TextField
            CustomTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

           PrimaryButton(title = "Register", onClick = { /*TODO*/ })

            Spacer(modifier = Modifier.height(16.dp))

            // Social Media Login Buttons
            Text("Or register with", modifier = Modifier.padding(bottom = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialLoginButton("Google", Color(0xFFDB4437)) // Google Red
                SocialLoginButton("Facebook", Color(0xFF4267B2)) // Facebook Blue
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Already have an account link
            Text(
                text = "Already have an account? Login",
                color = PrimaryColor, // Matching color with header
                modifier = Modifier.clickable {
                    // Handle navigation to login screen
                },
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}