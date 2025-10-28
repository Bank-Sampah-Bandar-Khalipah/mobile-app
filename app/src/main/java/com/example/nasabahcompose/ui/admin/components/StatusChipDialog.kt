package com.example.nasabahcompose.ui.admin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusChipDialog(
    initialStatus: String,
    modifier: Modifier = Modifier
) {
    var selectedStatus by remember { mutableStateOf(initialStatus) }
    val options = listOf("Berhasil", "Menunggu", "Gagal")

    var showDialog by remember { mutableStateOf(false) }

    val chipColor = when (selectedStatus) {
        "Berhasil" -> Color.Green.copy(alpha = 0.3f)
        "Menunggu" -> Color.Yellow.copy(alpha = 0.3f)
        "Gagal" -> Color.Red.copy(alpha = 0.3f)
        else -> Color.Gray.copy(alpha = 0.3f)
    }

    val textColor = when (selectedStatus) {
        "Berhasil" -> Color(0xFF006400) // Dark Green
        "Menunggu" -> Color(0xFF947800) // Dark Yellow
        "Gagal" -> Color(0xFFB00020) // Dark Red
        else -> Color.Black
    }

    // 3. Box to respect the Modifier
    Box(modifier = modifier) {
        AssistChip(
            onClick = { showDialog = true }, // Set showDialog to true to open
            label = {
                Text(
                    text = selectedStatus,
                    color = textColor,
                    fontSize = 12.sp
                )
            },
            shape = CircleShape,
            colors = AssistChipDefaults.assistChipColors(
                containerColor = chipColor
            ),
            border = BorderStroke(1.dp, Color.Gray)
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Pilih Status")
            },
            text = {
                // 5. Column to hold the options
                Column {
                    options.forEach { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedStatus = option
                                    showDialog = false
                                    // TODO: Add logic here to update your ViewModel or backend
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}