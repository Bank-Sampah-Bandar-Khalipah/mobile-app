package com.example.nasabahcompose.ui.nasabah.screens.deposit

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.dummy.dummySetoranList
import com.example.nasabahcompose.model.Setoran
import com.example.nasabahcompose.ui.nasabah.components.commons.CommonHeader

fun getSampahIcon(jenis: String): Int {
    return when (jenis.lowercase()) {
        "botol plastik", "botol" -> R.drawable.botol
        "kardus" -> R.drawable.kardus
        "kertas" -> R.drawable.kertas
        else -> R.drawable.ic_report
    }
}

@Composable
fun RincianLaporanScreen(
    navController: NavController,
    idLaporan: String
) {
    val setoran: Setoran = dummySetoranList.firstOrNull { it.id == idLaporan }
        ?: dummySetoranList.first()

    val totalBeratRounded = String.format("%.2f", setoran.totalBerat)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F5F5))
    ) {
        CommonHeader(
            title = "Laporan Setoran Sampah",
            navController = navController,
            backgroundColor = Color(0xFFF7F5F5)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Card Header "Rincian Laporan"
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_report),
                        contentDescription = null,
                        tint = Color(0xFF0A2B6E),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Rincian Laporan",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card Detail Setoran
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Row 1: ID Laporan dan Status
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "ID Laporan",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = setoran.id,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Status",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = setoran.status,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Row 2: Nama Petugas dan Tanggal & Waktu
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Nama Petugas",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = setoran.namaPetugas,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Tanggal & Waktu",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = setoran.tanggal,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // List Sampah dengan format baru
                    setoran.sampah.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            // Nama jenis sampah
                            Text(
                                text = item.jenis,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.weight(1f)
                            )

                            // Separator |
                            Text(
                                text = "|",
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            // Berat
                            Text(
                                text = "${String.format("%.1f", item.beratKg)} Kg",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.width(80.dp)
                            )

                            // Harga per kg (asumsi dari poin, bisa disesuaikan)
                            val hargaPerKg = (item.poin / item.beratKg).toInt()
                            Text(
                                text = "Rp${String.format("%,d", hargaPerKg)} s.d",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(end = 4.dp)
                            )

                            // Total harga item
                            Text(
                                text = "Rp${String.format("%,d", item.poin)}",
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color(0xFFE0E0E0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Total dengan format baru
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Total Berat
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Total",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Text(
                                text = " | ",
                                fontSize = 16.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )

                            Text(
                                text = "$totalBeratRounded Kg",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }

                        // Total Rupiah
                        Text(
                            text = "Rp${String.format("%,d", setoran.totalPoin)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF0A2B6E)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RincianLaporanPreview() {
    val exampleId = if (dummySetoranList.isNotEmpty()) dummySetoranList.first().id else "S001"
    RincianLaporanScreen(
        navController = rememberNavController(),
        idLaporan = exampleId
    )
}