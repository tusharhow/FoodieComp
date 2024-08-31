package com.example.foodiecomp.src.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiecomp.ui.theme.PrimaryColor
import com.example.foodiecomp.ui.theme.PrimaryLightColor

@Composable
 fun PrimaryButton(title: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick =  onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp ),
        colors = ButtonDefaults.buttonColors(containerColor = if (enabled) PrimaryColor else PrimaryLightColor),
    ) {
        Text(text = title, style = TextStyle(
            color = if (enabled)  Color.White else PrimaryColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        ))
    }
}