package com.example.nasabahcompose.ui.nasabah.screens.components.history

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasabahcompose.data.HistoryItem

@Composable
fun HistoryRow(item: HistoryItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Text(item.id.toString().padStart(2, '0'))
        }

        Box(
            modifier = Modifier
                .weight(1.5f),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Text(item.date)
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Text(item.amount)
        }

        Box(
            modifier = Modifier
                .weight(1.5f),
            contentAlignment = Alignment.Center
        ) {
            StatusBadge(item.status)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryRow() {
    HistoryRow(
        HistoryItem(1, "23/07/2025", "Rp 15.000", "Berhasil")
    )
}
