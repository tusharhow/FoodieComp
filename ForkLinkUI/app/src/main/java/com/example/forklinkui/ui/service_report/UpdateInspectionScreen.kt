package com.example.forklinkui.ui.service_report


import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.components.GradientButton
import com.example.forklinkui.components.UpdateJobCard

@Composable
fun UpdateInspectionScreen(onBack: () -> Unit = {}) {
    var showDetailScreen by remember { mutableStateOf(false) }

    if (!showDetailScreen) {
        UpdateInspectionContent(
            onJobCardTap = { showDetailScreen = true },
            onBack = onBack
        )
    } else {
        UpdateInspectionReportDetails(onBack = { showDetailScreen = false })
    }
}

@Composable
fun UpdateInspectionContent(onJobCardTap: () -> Unit, onBack: () -> Unit) {
    val items = listOf(
        "Lubricate truck in accordance with Lubrication schedule",
        "Lubricate truck in accordance with Lubrication schedule",
        // Additional items...
    )

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF164473), Color(0xFF2A80D9))
    )
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(1.dp, Color.Gray.copy(alpha = 0.15f), RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Text(
                    text = "Select Job Number To Update Inspection Report",
                    style = MaterialTheme.typography.h5.copy(fontSize = 16.sp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E3A8A))
                        .padding(16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items.size) { index ->
                        UpdateJobCard(
                            jobId = "TJOB-001",
                            time = "Time: 1:31 AM",
                            complainType = "Complain Type: 1",
                            date = "Date: 11 June 2022",
                            complainDescription = "Complain Description: 1",
                            inspectionDate = "Inspection Date: 11 June 2022",
                            previousInspections = "Previous Inspections: 1",
                            onTap = onJobCardTap
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .padding(vertical = 4.dp)
                    .shadow(4.dp, RoundedCornerShape(50))
            ) {
                OutlinedButton(
                    onClick = onBack, // Invoke onBack to navigate back
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffEBF2FC)),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.button.copy(
                            color = Color(0xFF1E3A8A),
                            fontSize = 12.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp * 5))

            // Submit Response Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .padding(vertical = 4.dp)
                    .shadow(4.dp, RoundedCornerShape(50))
            ) {
                GradientButton(
                    text = "Submit Response",
                    onClick = { /* Handle submit action */ },
                    modifier = Modifier.fillMaxSize(),
                    gradient = gradient
                )
            }
        }
    }
}
