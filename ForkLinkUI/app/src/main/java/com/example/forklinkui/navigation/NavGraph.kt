package com.example.forklinkui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.forklinkui.ui.HomeScreen
import com.example.forklinkui.ui.service_report.ServiceReportScreen
import com.example.forklinkui.ui.service_report.UpdateInspectionReportDetails


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {

            HomeScreen(
                navController = navController,
            )
        }

        composable(route = Screen.ServiceReport.route) {
            ServiceReportScreen(navController, onBackClick = { navController.popBackStack() })
        }

        composable(route = Screen.Testy.route) {
            UpdateInspectionReportDetails(
                onBack = { navController.popBackStack() }
            )
        }

    }

}