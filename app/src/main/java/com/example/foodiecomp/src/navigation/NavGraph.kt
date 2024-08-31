package com.example.foodiecomp.src.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodiecomp.src.views.auth.LoginScreen
import com.example.foodiecomp.src.views.auth.RegisterScreen
import com.example.foodiecomp.src.views.get_started.GetStartedScreen
import com.example.foodiecomp.src.views.onboarding.OnboardingPage
import com.example.foodiecomp.src.views.onboarding.OnboardingScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.OnboardScreen.route
    ) {
        composable(route = Screen.OnboardScreen.route) {
            OnboardingScreen(
                pages = listOf(
                    OnboardingPage( "Nearby restaurants", "You don't have to go far to find a good restaurant,\n" +
                            "we have provided all the restaurants that is \n" +
                            "near you","onb1.svg",),
                    OnboardingPage("Select the Favorites Menu", "Now eat well, don't leave the house,You can \n" +
                            "choose your favorite food only with \n" +
                            "one click","onb2.svg",),
                    OnboardingPage( "Good food at a cheap price", "You can eat at expensive restaurants with\n" +
                            "affordable price","onb3.svg",)
                ),
                onFinish = {
                    navController.navigate(Screen.GetStarted.route)
                }
            )
        }
          composable(route = Screen.GetStarted.route) {
            GetStartedScreen(
                navHostController = navController
            )
              }
            composable(route = Screen.LoginScreen.route) {
                 LoginScreen()
            }
            composable(route = Screen.RegisterScreen.route) {
               RegisterScreen()
            }
        }

    }
