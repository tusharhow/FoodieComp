package com.example.forklinkui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.forklinkui.R
import com.example.forklinkui.uitheme.Color


@Composable
fun BatteryView(percent: Int, isCharging: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(percent / 100f)
                .background(Color.Black)
                .align(Alignment.BottomCenter)
        )
        if (isCharging) {
            Icon(
                painter = painterResource(id = R.drawable.ic_batter_power),
                contentDescription = "Charging",
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}