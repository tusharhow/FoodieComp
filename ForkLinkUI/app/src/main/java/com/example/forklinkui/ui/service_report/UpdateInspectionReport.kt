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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.forklinkui.components.GradientButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.forklinkui.ui.job.FormField
import com.example.forklinkui.ui.job.MultilineFormField

@Composable
fun UpdateInspectionReportDetails(onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    var typeOfService by remember { mutableStateOf("") }
    var equipmentCondition by remember { mutableStateOf("") }
    var assistantTechnician by remember { mutableStateOf("") }
    var expenseOccurred by remember { mutableStateOf("") }
    var expenseDetails by remember { mutableStateOf("") }
    var inspectionFindings by remember { mutableStateOf("") }
    var technicalDiagnosis by remember { mutableStateOf("") }
    var partsTobeReplaced by remember { mutableStateOf("") }
    var actionsRequired by remember { mutableStateOf("") }
    var descriptionExpense by remember { mutableStateOf("") }
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
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            )
    ) {
        // Content inside the border
        Box(
            modifier = Modifier
                .weight(1f)  // Takes available space before the buttons
                .fillMaxWidth()
                .border(1.dp, Color.Gray.copy(alpha = 0.15f), RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 2.dp, bottomEnd = 2.dp))
                .clip(RoundedCornerShape(
                    topStart = 5.dp,
                    topEnd = 5.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Title
                Text(
                    text = "Inspection Report",
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E3A8A))
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .verticalScroll(scrollState)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)) {
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
                                label = "Resolved Temporarily",
                                value = assistantTechnician,
                                onValueChange = { assistantTechnician = it },
                                isDropdown = true
                            )
                            MultilineFormField(
                                label = "Technical Diagnosis",
                                value = technicalDiagnosis,
                                onValueChange = { technicalDiagnosis = it }
                            )
                            MultilineFormField(
                                label = "Actions Required ",
                                value = actionsRequired,
                                onValueChange = { actionsRequired = it }
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        ) {
                            FormField(
                                label = "Operable Condition",
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
                                label = "Parts To Be Replaced",
                                value = partsTobeReplaced,
                                onValueChange = { partsTobeReplaced = it }
                            )
                            MultilineFormField(
                                label = "Description Of Expense  (If Any)",
                                value = descriptionExpense,
                                onValueChange = { descriptionExpense = it }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Buttons outside the bordered Box
        Spacer(modifier = Modifier.height(10.dp)) // Add some space between the Box and the buttons

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
                    onClick = {onBack() },
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

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun FormField(
//    label: String,
//    value: String,
//    onValueChange: (String) -> Unit,
//    isDropdown: Boolean = false,
//    suffix: String = "",
//    keyboardType: KeyboardType = KeyboardType.Text,
//    hint: String = "Select" // Default hint for dropdowns
//) {
//    Column(modifier = Modifier.padding(vertical = 8.dp)) {
//        Text(text = label, style = MaterialTheme.typography.body1.copy(
//            fontWeight = FontWeight.Bold
//        ))
//        Spacer(modifier = Modifier.height(4.dp))
//        if (isDropdown) {
//            var expanded by remember { mutableStateOf(false) }
//            ExposedDropdownMenuBox(
//                expanded = expanded,
//                onExpandedChange = { expanded = !expanded }
//            ) {
//                OutlinedTextField(
//                    value = value,
//                    onValueChange = {},
//                    readOnly = true,
//                    label = { Text(label) },
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable(onClick = { expanded = true }),
//                    placeholder = { Text(hint) }
//                )
//                ExposedDropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    // Add your dropdown items here
//                    DropdownMenuItem(onClick = {
//                        onValueChange("Option 1")
//                        expanded = false
//                    }) {
//                        Text("Option 1")
//                    }
//                    // Add more options as needed
//                }
//            }
//        } else {
//            OutlinedTextField(
//                value = value,
//                onValueChange = onValueChange,
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
//                label = { Text(label) },
//                placeholder = { Text(hint) },
//                trailingIcon = if (suffix.isNotEmpty()) {
//                    { Text(suffix) }
//                } else null,
//            )
//        }
//    }
//}

//@Composable
//fun MultilineFormField(
//    label: String,
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//    Column(modifier = Modifier.padding(vertical = 8.dp)) {
//        Text(text = label, style = MaterialTheme.typography.body1.copy(
//            fontWeight = FontWeight.Bold
//        ))
//        Spacer(modifier = Modifier.height(6.dp))
//        TextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp),
//            maxLines = 8
//        )
//    }
//}