package com.example.foodiecomp.src.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object OnboardScreen : Screen("onboard")
    data object GetStarted : Screen("get_started")
    data object LoginScreen : Screen("login")
    data object RegisterScreen : Screen("register")
    data object OtpScreen : Screen("otp")

}