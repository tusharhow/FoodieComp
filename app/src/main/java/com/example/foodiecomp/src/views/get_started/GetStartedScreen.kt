package com.example.foodiecomp.src.views.get_started

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodiecomp.src.components.PrimaryButton
import com.example.foodiecomp.src.components.SvgImage
import com.example.foodiecomp.src.components.TermsAndConditionAnnotedText
import com.example.foodiecomp.src.navigation.Screen

@Composable
fun GetStartedScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SvgImage(
            assetName = "get_started.svg",
            modifier = Modifier.size(250.dp),
        )
        Spacer(modifier = Modifier.size(16.dp * 3))
        Text(text = "Welcome", style = TextStyle(
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        ))
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Before enjoying FoodieComp services\n" +
                    "Please register first",
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(title = "Create Account", onClick = {
            navHostController.navigate(Screen.RegisterScreen.route)
        })
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(title = "Login", onClick = {
            navHostController.navigate(Screen.LoginScreen.route)
        }, enabled = false)
        Spacer(modifier = Modifier.height(16.dp * 2))
        TermsAndConditionAnnotedText()
        Spacer(modifier = Modifier.height(16.dp * 2))
    }
}



