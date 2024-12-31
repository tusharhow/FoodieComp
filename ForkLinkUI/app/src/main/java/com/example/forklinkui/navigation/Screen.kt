package com.example.forklinkui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object ServiceReport : Screen("service_report")
    data object TechnicianMainProfileDashboard : Screen("technician_main_profile_dashboard")
    data object TechnicianLandingFragment : Screen("technician_landing_fragment")
    data object Testy : Screen("testy")

}