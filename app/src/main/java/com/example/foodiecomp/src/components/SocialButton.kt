package com.example.foodiecomp.src.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SocialLoginButton(text: String, color: Color) {
    Button(
        onClick = {
            // Handle social login here
        },
        modifier = Modifier
            .size(50.dp), // Round button
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text[0].toString(), color = Color.White) // Display the first letter of the social platform as a placeholder
    }
}