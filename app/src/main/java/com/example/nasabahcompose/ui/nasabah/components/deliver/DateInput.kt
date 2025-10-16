package com.example.nasabahcompose.ui.nasabah.components.deliver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DateDropdown(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    availableDates: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Tanggal", style = MaterialTheme.typography.subtitle1)
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedDate.isNotBlank()) selectedDate else "Pilih Tanggal",
                    color = if (selectedDate.isNotBlank()) Color.Black else Color.Gray
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            availableDates.forEach { date ->
                DropdownMenuItem(onClick = {
                    onDateSelected(date)
                    expanded = false
                }) {
                    Text(text = date)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateDropdown() {
    val sampleDates = listOf(
        "11 Agustus 2025",
        "25 Agustus 2025",
        "08 September 2025",
        "22 September 2025"
    )
    var selectedDate by remember { mutableStateOf("") }

    DateDropdown(
        selectedDate = selectedDate,
        onDateSelected = { selectedDate = it },
        availableDates = sampleDates
    )
}
