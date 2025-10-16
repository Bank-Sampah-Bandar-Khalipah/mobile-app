package com.example.nasabahcompose.ui.nasabah.components.deliver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerField(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Tanggal",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(25.dp))
                .border(1.dp, Color(0xFFD0D0D0), RoundedCornerShape(25.dp))
                .clickable { showDialog = true }
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedDate.ifEmpty { "Pilih Tanggal" },
                    fontSize = 14.sp,
                    color = if (selectedDate.isEmpty()) Color(0xFF9E9E9E) else Color.Black
                )
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Kalender",
                    tint = Color(0xFF0A2B6E),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

    if (showDialog) {
        CustomDatePickerDialog(
            onDateSelected = { date ->
                onDateSelected(date)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun CustomDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = remember { Calendar.getInstance() }
    var currentMonth by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var currentYear by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }

    val today = Calendar.getInstance()
    val monthFormat = SimpleDateFormat("MMMM yyyy", Locale("id"))
    val dayFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id"))

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Header
                Text(
                    text = "Tentukan Tanggal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Month/Year selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (currentMonth == 0) {
                            currentMonth = 11
                            currentYear--
                        } else {
                            currentMonth--
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous month",
                            tint = Color.Black
                        )
                    }

                    calendar.set(currentYear, currentMonth, 1)
                    Text(
                        text = monthFormat.format(calendar.time),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    IconButton(onClick = {
                        if (currentMonth == 11) {
                            currentMonth = 0
                            currentYear++
                        } else {
                            currentMonth++
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next month",
                            tint = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Days of week header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val daysOfWeek = listOf("Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab")
                    daysOfWeek.forEach { day ->
                        Text(
                            text = day,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF666666),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calendar grid
                val tempCal = Calendar.getInstance()
                tempCal.set(currentYear, currentMonth, 1)
                val firstDayOfWeek = tempCal.get(Calendar.DAY_OF_WEEK) - 1
                val daysInMonth = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH)

                // Calculate total cells needed
                val totalCells = firstDayOfWeek + daysInMonth
                val rows = (totalCells + 6) / 7

                Column {
                    for (row in 0 until rows) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (col in 0..6) {
                                val cellIndex = row * 7 + col
                                val day = cellIndex - firstDayOfWeek + 1

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .padding(2.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (day in 1..daysInMonth) {
                                        val dateToCheck = Calendar.getInstance()
                                        dateToCheck.set(currentYear, currentMonth, day)

                                        // Disable today and past dates
                                        val isDisabled = dateToCheck.get(Calendar.YEAR) < today.get(Calendar.YEAR) ||
                                                (dateToCheck.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                                                        dateToCheck.get(Calendar.DAY_OF_YEAR) <= today.get(Calendar.DAY_OF_YEAR))

                                        val backgroundColor = if (isDisabled) {
                                            Color.Transparent
                                        } else {
                                            Color.Transparent
                                        }

                                        val textColor = if (isDisabled) {
                                            Color(0xFFCCCCCC)
                                        } else {
                                            Color.Black
                                        }

                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(CircleShape)
                                                .background(backgroundColor)
                                                .then(
                                                    if (!isDisabled) {
                                                        Modifier.clickable {
                                                            tempCal.set(currentYear, currentMonth, day)
                                                            onDateSelected(dayFormat.format(tempCal.time))
                                                        }
                                                    } else {
                                                        Modifier
                                                    }
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = day.toString(),
                                                fontSize = 14.sp,
                                                color = textColor,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0A2B6E)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Pilih Tanggal",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun TimePickerField(
    selectedTime: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val timeOptions = listOf("09:00", "12:00", "15:00")

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Waktu",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.White, RoundedCornerShape(25.dp))
                    .border(1.dp, Color(0xFFD0D0D0), RoundedCornerShape(25.dp))
                    .clickable { expanded = true }
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedTime.ifEmpty { "Pilih Waktu" },
                        fontSize = 14.sp,
                        color = if (selectedTime.isEmpty()) Color(0xFF9E9E9E) else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color(0xFF0A2B6E),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(Color.White)
            ) {
                timeOptions.forEach { time ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = time,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            onTimeSelected(time)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}