package com.example.foodiecomp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.foodiecomp.src.navigation.SetupNavGraph
import com.example.foodiecomp.src.views.get_started.GetStartedScreen
import com.example.foodiecomp.src.views.onboarding.OnboardingPage
import com.example.foodiecomp.src.views.onboarding.OnboardingScreen
import com.example.foodiecomp.ui.theme.FoodieCompTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieCompTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    var navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

