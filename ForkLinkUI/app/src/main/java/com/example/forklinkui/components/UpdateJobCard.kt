package com.example.forklinkui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forklinkui.R
import com.example.forklinkui.components.GradientButton



@Composable
fun UpdateJobCard(
    jobId: String,
    time: String,
    complainType: String,
    date: String,
    complainDescription: String,
    inspectionDate: String,
    previousInspections: String,
    onTap: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFebf2fc)) // Card background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() // External padding for the whole row
                .background(Color(0xFFebf2fc))
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(16.dp)) // External padding for the whole row
            Image(
                painter = painterResource(id = R.drawable.tile),
                contentDescription = "Check Icon",
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = jobId,
                style = TextStyle(
                    fontSize = 10.sp,

                ),
                modifier = Modifier.padding(top = 5.dp),
                color = Color(0xff164473),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column { // Padding for the entire Column
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = "Time Icon",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = time,
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                        color = Color.Gray,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Cancel Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = complainType,
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontSize = 10.sp
                        ),
                        color = Color.Gray,
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cal),
                        contentDescription = "Date Icon",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontSize = 10.sp
                        ),
                        color = Color.Gray,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cal),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = complainDescription,
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontSize = 10.sp
                        ),
                        color = Color.Gray,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f)) // Push the arrow to the end

            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow Icon",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { onTap() }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        // Divider at the bottom of the entire row section, inside the card.
        Divider(
            color = Color.LightGray, // Make sure the color stands out
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Standard divider height
        )
    }
}
