package com.example.forklinkui.ui.job


import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.R
import com.example.forklinkui.components.GradientButton
import com.example.forklinkui.ui.service_report.SparePart
import com.example.forklinkui.ui.service_report.SparePartRow

@Composable
fun JobCompletionDetails(onBack: () -> Unit) {
    var typeOfService by remember { mutableStateOf("") }
    var equipmentCondition by remember { mutableStateOf("") }
    var assistantTechnician by remember { mutableStateOf("") }
    var expenseOccurred by remember { mutableStateOf("") }
    var expenseDetails by remember { mutableStateOf("") }
    var inspectionFindings by remember { mutableStateOf("") }
    var sparePartsList = remember { mutableStateListOf<SparePart>() }

    val gradient =
        Brush.horizontalGradient(listOf(Color(0xFF164473), Color(0xFF2A80D9)))
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
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 10.dp)
    ) {
        // Box to apply the border around the title and content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    Color.Gray.copy(alpha = 0.15f),
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 2.dp,
                        bottomEnd = 2.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Color.White)
                .weight(1f) // This ensures the scrolling area does not push buttons off-screen
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Title
                Text(
                    text = "Job Completion Report",
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E3A8A))
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                // Scrollable content area for form and spare parts list
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        // Form fields and spare parts section
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            // Form fields in two columns
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                            ) {
                                FormField(
                                    label = "Designated Assistant Technician",
                                    value = typeOfService,
                                    onValueChange = { typeOfService = it },
                                    isDropdown = true
                                )
                                FormField(
                                    label = "Problem Resolved After Job Completion",
                                    value = assistantTechnician,
                                    onValueChange = { assistantTechnician = it },
                                    isDropdown = true
                                )
                                MultilineFormField(
                                    label = "Actions Taken",
                                    value = expenseDetails,
                                    onValueChange = { expenseDetails = it }
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            ) {
                                FormField(
                                    label = "Meter Reading Hour At Job Completion",
                                    value = equipmentCondition,
                                    onValueChange = { equipmentCondition = it },
                                    isDropdown = true
                                )
                                FormField(
                                    label = "Expense Occurred (If Any)",
                                    value = expenseOccurred,
                                    onValueChange = { expenseOccurred = it },
                                    suffix = "PKR",
                                    keyboardType = KeyboardType.Number
                                )
                                MultilineFormField(
                                    label = "Description Of Expense (If Any)",
                                    value = inspectionFindings,
                                    onValueChange = { inspectionFindings = it }
                                )
                            }
                        }
                    }

                    // Spare parts section
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = 0.dp,
                            backgroundColor = Color(0xFFEBF2FC)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.setting),
                                    contentDescription = "Spare Parts Icon",
                                    modifier = Modifier.size(35.dp),
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = "Spare Parts To Replace",
                                    style = MaterialTheme.typography.h6.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(0xFF1E3A8A)
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {
                                        sparePartsList.add(SparePart("", "0"))
                                    }
                                ) {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = "Add Spare Part",
                                        tint = Color(0xFF1E3A8A),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Display the list of spare parts using LazyColumn for efficient scrolling
                    items(sparePartsList.size) { index ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            elevation = 0.dp,
                        ) {
                            SparePartRow(
                                sparePart = sparePartsList[index],
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

        // Buttons at the bottom, outside the scrollable area
        Spacer(modifier = Modifier.height(10.dp)) // Add space between the Box and the buttons

        Row(
            modifier = Modifier.fillMaxWidth(),
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
                    onClick = { onBack() },
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
    }



}


@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isDropdown: Boolean = false,
    suffix: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    hint: String = "",
    modifier: Modifier = Modifier
) {
    var showDropdown by remember { mutableStateOf(false) }
    var anchorSize by remember { mutableStateOf(IntSize.Zero) }
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

    // Cleanup status bar flags when component is disposed
    DisposableEffect(Unit) {
        onDispose {
            activity?.let { act ->
                act.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                act.window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        )
            }
        }
    }

    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (isDropdown) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .onSizeChanged { anchorSize = it }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            BorderStroke(0.1.dp, Color(0xFF174172).copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xffF3F3F3))
                        .clickable { showDropdown = !showDropdown }
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = value.ifEmpty { hint },
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = if (value.isEmpty()) Color.Gray else Color.Black
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Dropdown arrow",
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFF5D60EF),
                        )
                    }
                }

                MaterialTheme(
                    colors = MaterialTheme.colors.copy(surface = Color.White),
                    shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(8.dp))
                ) {
                    DropdownMenu(
                        expanded = showDropdown,
                        onDismissRequest = { showDropdown = false },
                        modifier = Modifier
                            .width(with(density) { anchorSize.width.toDp() })
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                BorderStroke(0.1.dp, Color(0xFF174172).copy(alpha = 0.5f)),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                onValueChange("Option 1")
                                showDropdown = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Option 1",
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                        Divider(
                            color = Color(0xFF174172).copy(alpha = 0.1f),
                            thickness = 0.5.dp
                        )
                        DropdownMenuItem(
                            onClick = {
                                onValueChange("Option 2")
                                showDropdown = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Option 2",
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(
                        BorderStroke(0.1.dp, Color(0xFF174172).copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xffF3F3F3)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    placeholder = { Text(hint, fontSize = 12.sp) },
                    trailingIcon = if (suffix.isNotEmpty()) {
                        {
                            Text(
                                suffix,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Gray.copy(alpha = 0.5f),
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    } else null
                )
            }
        }
    }
}


@Composable
fun MultilineFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(text = label, style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ))
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(
                    BorderStroke(
                        0.1.dp,
                        Color(0xFF174172).copy(
                            alpha = 0.5f
                        )
                    ), // Set desired border width and color
                    shape = RoundedCornerShape(4.dp) // Optional: Adjust the corner radius
                )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xfff3f3f3), RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                value = value,
                onValueChange = { onValueChange(it) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                maxLines = 2,
                textStyle = MaterialTheme.typography.caption,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xfff3f3f3),
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f)
                )
            )
        }
    }
}