package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.data.HistoryItem
import com.example.nasabahcompose.ui.nasabah.components.commons.CommonScreenHeader
import com.example.nasabahcompose.ui.nasabah.components.commons.MyNavbarBar
import com.example.nasabahcompose.ui.nasabah.components.history.HistoryTable

// Fungsi untuk menghitung jumlah data tiap status
fun getStatusSummary(items: List<HistoryItem>): Map<String, Int> {
    return items.groupingBy { it.status }.eachCount()
}

@Composable
fun HistoryScreen(username: String, navController: NavController) {
    val historyItems = (1..10).map {
        HistoryItem(
            it,
            "23/07/2025",
            "Rp 15.000",
            when (it % 3) {
                0 -> "Gagal"
                1 -> "Berhasil"
                else -> "Proses"
            }
        )
    }

    val statusSummary = getStatusSummary(historyItems)

    Scaffold(
        bottomBar = { MyNavbarBar(navController = navController, username = username) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header konsisten
            CommonScreenHeader(title = "Pencairan Dana")

            // Ringkasan status
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF0A2B6E), RoundedCornerShape(20.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listOf("Berhasil", "Proses", "Gagal").forEach { status ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = status,
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = (statusSummary[status] ?: 0).toString().padStart(2, '0'),
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tabel histori
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 600.dp)
                        .padding(16.dp, 0.dp)
                ) {
                    HistoryTable(items = historyItems)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen(username = "Yusuf", navController = rememberNavController())
}