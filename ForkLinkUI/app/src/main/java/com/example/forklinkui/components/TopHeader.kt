package com.example.forklinkui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.R
import com.example.forklinkui.uitheme.Color

@Composable
fun TopHeader(
    onBackClick: () -> Unit,
    onPowerClick: () -> Unit,
    onHomeClick: () -> Unit,
    currentTime: String,
    batteryPercent: Int,
    isCharging: Boolean,
    isBluetoothActive: Boolean,
    isInternetActive: Boolean,
    isWifiActive: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Logo (now completely to the left)
        Image(
            painter = painterResource(id = R.drawable.forklink_logo),
            contentDescription = "Fork Link Logo",
            modifier = Modifier.size(150.dp) // Adjust size as needed
        )

        Spacer(modifier = Modifier.weight(1f))

        // Center Buttons
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onBackClick) {
                Image(
                    painter = painterResource(id = R.drawable.app_back_icon),
                    contentDescription = "Back",
                    modifier = Modifier.size(35.dp) // Adjust size as needed
                )
            }
            IconButton(onClick = onPowerClick) {
                Image(
                    painter = painterResource(id = R.drawable.logout_icon),
                    contentDescription = "Power",
                    modifier = Modifier.size(50.dp) // Adjust size as needed
                )
            }
            IconButton(onClick = onHomeClick) {
                Image(
                    painter = painterResource(id = R.drawable.app_home_icon),
                    contentDescription = "Home",
                    modifier = Modifier.size(35.dp) // Adjust size as needed
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Right Side Icons
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.connectivity_icon),
                contentDescription = "Connectivity",
                modifier = Modifier.padding(end = 10.dp)
            )
            Icon(
                painter = painterResource(id = if (isBluetoothActive) R.drawable.bluetooth_active_icon else R.drawable.bluetooth_active_icon),
                contentDescription = "Bluetooth",
                modifier = Modifier.padding(end = 10.dp)
            )
            Icon(
                painter = painterResource(id = if (isInternetActive) R.drawable.backup_restore_icon else R.drawable.backup_restore_icon),
                contentDescription = "Internet",
                modifier = Modifier.padding(end = 15.dp)
            )
            Icon(
                painter = painterResource(id = if (isWifiActive) R.drawable.wifi_active_icon else R.drawable.wifi_active_icon),
                contentDescription = "WiFi",
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = currentTime,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 10.dp)
            )
            Box(
                modifier = Modifier
                    .width(15.dp)
                    .height(25.dp)
            ) {
                BatteryView(
                    percent = batteryPercent,
                    isCharging = isCharging
                )
            }
            Text(
                text = "$batteryPercent%",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}