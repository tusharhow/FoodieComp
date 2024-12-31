package com.example.forklinkui.ui


import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.R
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import com.example.forklinkui.components.TopHeader
import com.example.forklinkui.ui.job.JobCompletionReport
import com.example.forklinkui.ui.maintenance.MaintenanceScreen
import com.example.forklinkui.ui.menu.TechnicianMenuComponent
import com.example.forklinkui.ui.service_report.ServiceReportScreen
import com.example.forklinkui.ui.service_report.UpdateInspectionScreen
import com.example.forklinkui.viewmodel.MenuItemsViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val menuItemsViewModel = remember { MenuItemsViewModel() }
    val showReportView = remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize()) {
        TopHeader(
            onBackClick = { /* Handle back click */ },
            onPowerClick = { /* Handle power click */ },
            onHomeClick = {
                menuItemsViewModel.setClickedItemIndex(4) // Set to a new index for ReportView
            },
            currentTime = "9:12 PM",
            batteryPercent = 85,
            isCharging = true,
            isBluetoothActive = true,
            isInternetActive = true,
            isWifiActive = true
        )

        Row(modifier = Modifier.weight(1f)) {
            // Left Sidebar
            Column(
                modifier = Modifier
                    .width(110.dp)
                    .height(480.dp)
                    .background(
                        Color(0xFFd0ddf1)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                TechnicianMenuComponent(menuItemsViewModel)
            }

            // Main Content
            Box(modifier = Modifier
                .weight(1f)
                .background(
                    Color(0xFFd0ddf1)
                )
                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
            ) {
                HomeScreenContent(navController, menuItemsViewModel)
            }
        }
    }
}

@Composable
fun HomeScreenContent(navController: NavHostController, menuItemsViewModel: MenuItemsViewModel) {
    val clickedItemIndex by menuItemsViewModel.clickedItemIndex.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (clickedItemIndex) {
            0 -> MaintenanceScreen(
                back = {
                    menuItemsViewModel.setClickedItemIndex(-1) // Reset to DefaultDashboardView
                }
            )
            1 -> ServiceReportScreen(navController, onBackClick = { menuItemsViewModel.setClickedItemIndex(-1)})
            2 -> JobCompletionReport(
                onBack = { menuItemsViewModel.setClickedItemIndex(-1) }
            )
            3 -> UpdateInspectionScreen(
                onBack = { menuItemsViewModel.setClickedItemIndex(-1) }
            )
            4 -> ReportView(
                onBack = { menuItemsViewModel.setClickedItemIndex(-1) }
            )
            else -> DefaultDashboardView()
        }

    }
}

@Composable
fun DefaultDashboardView() {
    val density = LocalDensity.current
    val context = LocalContext.current
    val activity = remember(context) {
        context as? Activity
    }

    // Keep status bar hidden consistently
    LaunchedEffect(Unit) {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            act.window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.White)
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileCard()
        DetailsCard()

    }
}

@Composable
fun ProfileCard() {

    Column(
        modifier = Modifier
            .width(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffd0ddf1))
            .padding(10.dp, 0.dp, 10.dp, 0.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.user),
            contentDescription = "Profile Icon",
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome, Rameez", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun DetailsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            DetailColumn(
                Modifier.weight(2f),
                "Login Time" to "9:00 AM",
                "Next Service Due" to "N/A",
                R.drawable.login_time_icon,
                R.drawable.date_calendar_icon
            )

            Spacer(modifier = Modifier.width(5.dp))

            DetailColumn(
                Modifier.weight(2f),
                "Pending Jobs" to "12",
                "Equipment Meter Hours" to "12",
                R.drawable.seat_belt_icon,
                R.drawable.idle_duration_icon
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        MaintenanceRow()
    }
}

@Composable
fun MaintenanceRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp),
                    painter = painterResource(id = R.drawable.login_time_icon),
                    contentDescription = "Maintenance Icon"
                )
                Text(
                    text = "Maintenance",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Enabled")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun DetailColumn(
    modifier: Modifier,
    firstDetail: Pair<String, String>,
    secondDetail: Pair<String, String>,
    firstIcon: Int,
    secondIcon: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailItem(firstDetail.first, firstDetail.second, firstIcon)
        Spacer(modifier = Modifier.height(25.dp))
        DetailItem(secondDetail.first, secondDetail.second, secondIcon)
    }
}

@Composable
fun DetailItem(label: String, value: String, iconRes: Int) {
    Row {
        Image(
            modifier = Modifier
                .width(15.dp)
                .height(15.dp),
            painter = painterResource(id = iconRes),
            contentDescription = "$label Icon"
        )
        Text(
            text = label,
            style = TextStyle(
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = value,
        style = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontSize = 14.sp
        ),
        modifier = Modifier.padding(10.dp, 0.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Divider(Modifier.fillMaxWidth())
}