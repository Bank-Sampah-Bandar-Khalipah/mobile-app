package com.example.nasabahcompose.ui.nasabah.screens.components.deliver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimeDropdown(
    selectedTime: String,
    onTimeSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val today = Calendar.getInstance()
    val currentTime = today.time

    // Format jam yang diizinkan
    val timeOptions = listOf("09:00", "12:00", "15:00")
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    // Filter jam yang masih tersedia hari ini
    val availableTimesToday = timeOptions.filter { timeString ->
        val date = timeFormat.parse(timeString)
        val calendarTime = Calendar.getInstance().apply {
            time = date!!
            set(Calendar.YEAR, today.get(Calendar.YEAR))
            set(Calendar.MONTH, today.get(Calendar.MONTH))
            set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH))
        }
        calendarTime.time.after(currentTime)
    }

    // Jika semua jam sudah lewat, simulasikan "pilih waktu 2 minggu depan"
    val isAllTimeOver = availableTimesToday.isEmpty()
    val displayOptions = if (isAllTimeOver) timeOptions else availableTimesToday

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Waktu", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .border(1.dp, Color.Gray, RoundedCornerShape(50))
                .clickable { expanded = true }
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (selectedTime.isNotBlank()) selectedTime else "Masukkan Waktu",
                    color = if (selectedTime.isNotBlank()) Color.Black else Color.Gray
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
        ) {
            displayOptions.forEach { time ->
                DropdownMenuItem(onClick = {
                    val finalTime = if (isAllTimeOver) "$time (2 minggu depan)" else time
                    onTimeSelected(finalTime)
                    expanded = false
                }) {
                    Text(finalTimeDisplay(time, isAllTimeOver))
                }
            }
        }
    }
}

// Fungsi pembantu menambahkan "(2 minggu depan)" jika perlu
private fun finalTimeDisplay(time: String, isTwoWeeksLater: Boolean): String {
    return if (isTwoWeeksLater) "$time (2 minggu depan)" else time
}

@Preview(showBackground = true)
@Composable
fun PreviewTimeDropdown() {
    var selectedTime by remember { mutableStateOf("") }

    TimeDropdown(
        selectedTime = selectedTime,
        onTimeSelected = { selectedTime = it }
    )
}
