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
//                    OnboardingScreen(
//                        pages = listOf(
//                            OnboardingPage( "Nearby restaurants", "You don't have to go far to find a good restaurant,\n" +
//                                    "we have provided all the restaurants that is \n" +
//                                    "near you","onb1.svg",),
//                            OnboardingPage("Select the Favorites Menu", "Now eat well, don't leave the house,You can \n" +
//                                    "choose your favorite food only with \n" +
//                                    "one click","onb2.svg",),
//                            OnboardingPage( "Good food at a cheap price", "You can eat at expensive restaurants with\n" +
//                                    "affordable price","onb3.svg",)
//                        ),
//                        onFinish = {  }
//                    )
                    GetStartedScreen()
                }
            }
        }
    }
}

