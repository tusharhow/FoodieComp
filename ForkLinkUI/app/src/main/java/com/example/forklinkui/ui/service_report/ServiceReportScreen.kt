package com.example.forklinkui.ui.service_report

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.forklinkui.R
import com.example.forklinkui.components.GradientButton
import com.example.forklinkui.ui.job.FormField
import com.example.forklinkui.ui.job.MultilineFormField
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.forklinkui.components.ServiceChecklistCard
import com.example.forklinkui.ui.maintenance.drawVerticalScrollbar
import kotlin.math.roundToInt



@Composable
fun ServiceReportScreen(navHostController: NavHostController, onBackClick: () -> Unit) {
    var typeOfService by remember { mutableStateOf("") }
    var equipmentCondition by remember { mutableStateOf("") }
    var assistantTechnician by remember { mutableStateOf("") }
    var expenseOccurred by remember { mutableStateOf("") }
    var expenseDetails by remember { mutableStateOf("") }
    var inspectionFindings by remember { mutableStateOf("") }
    var sparePartsList = remember { mutableStateListOf<SparePart>() }
    var currentSection by remember { mutableStateOf("Service Report") }

    var sparePartsPosition by remember { mutableStateOf(0f) }
    var checklistPosition by remember { mutableStateOf(0f) }

    val scrollState = rememberScrollState()
    val gradient = Brush.horizontalGradient(listOf(Color(0xFF164473), Color(0xFF2A80D9)))
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
    LaunchedEffect(scrollState.value, sparePartsPosition, checklistPosition) {
        currentSection = when {
            scrollState.value < sparePartsPosition -> "Service Report"
            scrollState.value < checklistPosition -> "Spare Parts To Replace"
            else -> "Service Checklist"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 0.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 5.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ))
                .background(Color.White)

        ) {
            // Header
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f),
                elevation = 4.dp
            ) {
                Text(
                    text = currentSection,
                    style = MaterialTheme.typography.h5.copy(fontSize = 16.sp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E3A8A))
                        .padding(16.dp)
                )
            }

            // Content with scrollbar
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                // Scrollable Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray.copy(alpha = 0.15f), RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 2.dp, bottomEnd = 2.dp))
                        .verticalScroll(scrollState)
                        .padding(
                            top = 16.dp,
                            start = 0.dp,
                            end = 16.dp,
                            bottom = 10.dp
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            FormField(
                                label = "Type Of Service Performed",
                                value = typeOfService,
                                onValueChange = { typeOfService = it },
                                isDropdown = true
                            )
                            FormField(
                                label = "Assistant Technician",
                                value = assistantTechnician,
                                onValueChange = { assistantTechnician = it },
                                isDropdown = true
                            )
                            MultilineFormField(
                                label = "Details Of Expense (If Any)",
                                value = expenseDetails,
                                onValueChange = { expenseDetails = it }
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp)
                        ) {
                            FormField(
                                label = "Equipment Was In Operable Condition",
                                value = equipmentCondition,
                                onValueChange = { equipmentCondition = it },
                                isDropdown = true
                            )
                            FormField(
                                label = "Expense Occurred (If Any)",
                                value = expenseOccurred,
                                onValueChange = { expenseOccurred = it },
                                keyboardType = KeyboardType.Number,
                                suffix = "PKR",

                            )
                            MultilineFormField(
                                label = "Technical Inspection Findings",
                                value = inspectionFindings,
                                onValueChange = { inspectionFindings = it }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    // Spare Parts Section
                    // Spare Parts Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                sparePartsPosition = coordinates.positionInRoot().y
                            },
                        elevation = 0.dp,
                        backgroundColor = Color(0xFFEBF2FC)
                    ) {
                        Column {
                            // Header with Add button
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.setting),
                                    contentDescription = "Spare Parts Icon",
                                    modifier = Modifier.size(35.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "Spare Parts To Replace",
                                    style = MaterialTheme.typography.h6.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1E3A8A),
                                        fontSize = 14.sp
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = { sparePartsList.add(SparePart("", "0")) }
                                ) {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = "Add Spare Part",
                                        tint = Color(0xFF1E3A8A),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }

                            // Spare Parts List
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                sparePartsList.forEachIndexed { index, sparePart ->
                                    Spacer(modifier = Modifier.height(0.dp))
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        elevation = 0.dp,
                                        backgroundColor = Color.White
                                    ) {
                                        SparePartRow(
                                            sparePart = sparePart,
                                            onSparePartChange = { updatedPart ->
                                                sparePartsList[index] = updatedPart
                                            },
                                            onDelete = {
                                                sparePartsList.removeAt(index)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Service Checklist Section
                    Box(
                        modifier = Modifier
                            .onGloballyPositioned { coordinates ->
                                checklistPosition = coordinates.positionInRoot().y
                            }
                    ) {
                        ServiceChecklistCard()
                    }
                }

                // Scrollbar
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .width(8.dp)
                ) {
                    // Background track
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(
                                Color.LightGray.copy(alpha = 0.1f),
                                RoundedCornerShape(4.dp)
                            )
                            .width(8.dp)
                    )

                    // Scrollbar thumb
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(8.dp)
                    ) {
                        val thumbHeight = with(LocalDensity.current) {
                            val viewportHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
                            val contentHeight = scrollState.maxValue + viewportHeight
                            (viewportHeight / contentHeight * viewportHeight).dp
                        }

                        val thumbPosition = with(LocalDensity.current) {
                            val maxScroll = scrollState.maxValue.toFloat()
                            val viewportHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
                            if (maxScroll > 0f) {
                                (scrollState.value.toFloat() / maxScroll * (viewportHeight - thumbHeight.toPx())).dp
                            } else {
                                0.dp
                            }
                        }

                        Box(
                            modifier = Modifier
                                .offset(y = thumbPosition)
                                .height(thumbHeight)
                                .fillMaxWidth()
                                .background(Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp)) // Add space between the Box and the buttons

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Button (Back) with a more realistic shadow effect
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(
                            horizontal = 0.dp,
                            vertical = 4.dp
                        ) // Adjusts positioning of the shadow
                        .shadow(
                            elevation = 4.dp, // Adjust elevation as needed
                            shape = RoundedCornerShape(50),
                            ambientColor = Color.Black.copy(alpha = 0.8f), // Light shadow color
                            spotColor = Color.Black.copy(alpha = 0.8f)
                        )
                ) {
                    OutlinedButton(
                        onClick = {onBackClick() },
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xffEBF2FC),
                        ),
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

                // Right Button (Submit Response) with the same shadow
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(
                            horizontal = 0.dp,
                            vertical = 4.dp
                        ) // Adjusts positioning of the shadow
                        .shadow(
                            elevation = 4.dp, // Adjust elevation as needed
                            shape = RoundedCornerShape(50),
                            ambientColor = Color.Black.copy(alpha = 0.8f),
                            spotColor = Color.Black.copy(alpha = 0.8f)
                        )
                ) {
                    GradientButton(
                        text = "Submit Response",
                        onClick = { /* Handle submit action */ },
                        modifier = Modifier.fillMaxSize(),
                        gradient = gradient
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun SparePartRow(
    sparePart: SparePart,
    onSparePartChange: (SparePart) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            var expanded by remember { mutableStateOf(false) }
            // Dropdown field with gradient border and filled background
            BasicTextField(
                value = sparePart.name,
                onValueChange = { /* Handled by dropdown */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xfff3f3f3)) // Filled background color
                    .border( // Gradient border
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .height(40.dp),
                enabled = false, // Disabling input since it's dropdown based
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (sparePart.name.isEmpty()) {
                                Text(
                                    text = "Select Spare Part",
                                    color = Color(0xff164473)
                                )
                            }
                            innerTextField() // Field for the dropdown value
                        }
                        Icon(
                            if (expanded) Icons.Filled.KeyboardArrowUp
                            else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "dropdown arrow",
                            tint = Color(0xFF1E3A8A)
                        )
                    }
                }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = !expanded } // Click to expand/collapse the dropdown
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { 300.dp })
            ) {
                listOf(
                    "Motor Belt",
                    "Air Filter",
                    "Oil Filter",
                    "Spark Plug",
                    "Battery",
                    "Brake Pad"
                ).forEach { part ->
                    DropdownMenuItem(onClick = {
                        onSparePartChange(sparePart.copy(name = part)) // Update the selected part
                        expanded = false
                    }) {
                        Text(text = part, style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0xff164473)
                        ))
                    }
                }
            }
        }

        QuantityTextField(
            value = sparePart.quantity,
            onValueChange = { newValue ->
                onSparePartChange(sparePart.copy(quantity = newValue))
            },
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 8.dp)
        )

        IconButton(
            onClick = onDelete,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.del),
                contentDescription = "",
                modifier = Modifier.size(35.dp),

                )
        }
    }
}

@Composable
fun QuantityTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = Color(0xff164473),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .width(150.dp)
            .height(40.dp) // Adjust height if needed
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xfff3f3f3))
            .border(
                width = 1.dp,
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 12.dp) // Add vertical padding to center text vertically
            .padding(horizontal = 0.dp), // Optional: Add horizontal padding if needed
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}

data class SparePart(
    val name: String,
    val quantity: String
)