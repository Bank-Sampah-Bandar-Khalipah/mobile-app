package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.nasabahcompose.ui.nasabah.screens.components.CommonHeader

/**
 * Helper: mapping icon berdasarkan jenis sampah.
 * Pastikan drawable: botol, kardus, kertas, ic_report ada.
 */
fun getSampahIcon(jenis: String): Int {
    return when (jenis.lowercase()) {
        "botol plastik", "botol" -> R.drawable.botol
        "kardus" -> R.drawable.kardus
        "kertas" -> R.drawable.kertas
        else -> R.drawable.ic_report
    }
}

/**
 * Rincian laporan yang mengambil data berdasarkan idLaporan dari dummySetoranList.
 * Desain dipertahankan persis.
 */
@Composable
fun RincianLaporanScreen(
    navController: NavController,
    idLaporan: String
) {
    // Ambil data setoran dari dummy; fallback ke item pertama jika tidak ditemukan
    val setoran: Setoran = dummySetoranList.firstOrNull { it.id == idLaporan } ?: dummySetoranList.first()

    // Hitung total berat (2 desimal) dan total poin sudah tersedia di model
    val totalBeratRounded = String.format("%.2f", setoran.totalBerat)

    // Jika hanya ada Botol Plastik saja dan user ingin tampilkan hanya satu (sesuai logika yg sempat ada),
    // kita ikuti logika yang diberikan: tampilkan hanya item pertama bila semua jenis sama dan botol plastik.
    val filteredItems = if (setoran.sampah.map { it.jenis.lowercase() }.distinct().size == 1 &&
        setoran.sampah.first().jenis.equals("Botol Plastik", ignoreCase = true)
    ) {
        listOf(setoran.sampah.first())
    } else {
        setoran.sampah
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F5F5))
    ) {
        // Header menggunakan CommonHeader
        CommonHeader(
            title = "Laporan Setoran Sampah",
            navController = navController,
            backgroundColor = Color(0xFFF7F5F5)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Card judul rincian
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_report),
                        contentDescription = null,
                        tint = Color(0xFF1A1A1A),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Rincian Laporan",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Baris 1: ID & Status
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "ID Laporan", fontSize = 12.sp, color = Color.Gray)
                            Text(text = setoran.id, fontWeight = FontWeight.SemiBold)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Status", fontSize = 12.sp, color = Color.Gray)
                            Text(text = setoran.status, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Baris 2: Nama Petugas & Tanggal
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Nama Petugas", fontSize = 12.sp, color = Color.Gray)
                            Text(text = setoran.namaPetugas, fontWeight = FontWeight.SemiBold)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Tanggal & Waktu", fontSize = 12.sp, color = Color.Gray)
                            Text(text = setoran.tanggal, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // List item sampah (sesuai filter)
                    filteredItems.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = getSampahIcon(item.jenis)),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.jenis, modifier = Modifier.weight(0.6f))
                            Spacer(modifier = Modifier.width(8.dp))
                            // berat 1 atau 2 desimal sesuai input
                            Text(text = "${String.format("%.2f", item.beratKg)} Kg", modifier = Modifier.width(100.dp))
                            Text(text = "${item.poin} Pts", modifier = Modifier.width(100.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFDDDDDD))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Total
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Total",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "$totalBeratRounded Kg",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.width(100.dp),
                            color = Color(0xFF0B1F5C)
                        )
                        Text(
                            text = "${setoran.totalPoin} Pts",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.width(100.dp),
                            color = Color(0xFF0B1F5C)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Preview: gunakan salah satu dummySetoranList untuk melihat tampilan persis.
 */
@Preview(showBackground = true)
@Composable
fun RincianLaporanPreview() {
    // ambil contoh dari dummy
    val exampleId = if (dummySetoranList.isNotEmpty()) dummySetoranList.first().id else "S001"
    RincianLaporanScreen(
        navController = rememberNavController(),
        idLaporan = exampleId
    )
}