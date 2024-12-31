package com.example.forklinkui.ui.maintenance

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.components.GradientButton
import com.example.forklinkui.components.SmallCheckbox

@Composable
fun MaintenanceScreen(back : () -> Unit) {
    val items = List(50) { "Lubricate truck in accordance with Lubrication schedule" } // Example items
    val selectedItems = remember { mutableStateListOf<Boolean>() }
    items.forEach { _ -> selectedItems.add(false) }
    val listState = rememberLazyListState()

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
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            )
    ) {
        // Content inside the border
        Box(
            modifier = Modifier
                .weight(1f)  // Takes available space before the buttons
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Title
                Text(
                    text = "Daily Maintenance Check List",
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E3A8A))
                        .padding(16.dp)
                )
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .drawVerticalScrollbar(listState)
                ) {
                    items(items.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SmallCheckbox( checked = selectedItems[index],
                                onCheckedChange = { selectedItems[index] = it },)

                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = items[index],
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 11.sp
                                ),
                                color = Color(0xff164473)
                            )
                        }
                    }
                }
            }
        }

        // Buttons outside the bordered Box
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
                    onClick = {
                        back()
                    },
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


fun Modifier.drawVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp,
): Modifier = composed {
    drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.firstVisibleItemIndex
        val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
        val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
        val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

        drawRect(
            color = Color.Black.copy(alpha = 0.2f), // Constant alpha for visibility
            topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
            size = Size(width.toPx(), scrollbarHeight)
        )
    }
}