package com.example.nasabahcompose.ui.nasabah.components.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasabahcompose.data.HistoryItem

@Composable
fun HistoryTable(items: List<HistoryItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp), // batasi tinggi agar tidak infinite
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            // Header Tabel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.5f), contentAlignment = Alignment.Center) {
                    Text("No.")
                }
                Box(modifier = Modifier.weight(1.5f), contentAlignment = Alignment.Center) {
                    Text("Tanggal")
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text("Nominal")
                }
                Box(modifier = Modifier.weight(1.5f), contentAlignment = Alignment.Center) {
                    Text("Status")
                }
            }
            Divider()
        }

        // Data Tabel
        items(items) { item ->
            HistoryRow(item)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryTable() {
    HistoryTable(
        listOf(
            HistoryItem(1, "23/07/2025", "Rp 15.000", "Berhasil"),
            HistoryItem(2, "23/07/2025", "Rp 15.000", "Proses"),
            HistoryItem(3, "23/07/2025", "Rp 15.000", "Gagal")
        )
    )
}
