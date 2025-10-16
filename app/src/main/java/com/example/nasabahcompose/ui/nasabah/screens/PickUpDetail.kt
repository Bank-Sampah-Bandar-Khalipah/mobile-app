package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import com.example.nasabahcompose.data.PickupDetail
import com.example.nasabahcompose.ui.nasabah.screens.components.CommonHeader

@Composable
fun PenjemputanScreen(
    navController: NavController,
    pickupDetail: PickupDetail,
    userAddress: String = "GJQ4+RGP, Kabupaten Deli Serdang"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F2F2))
    ) {
        // Header dengan style yang sama
        CommonHeader(
            title = "Penjemputan",
            navController = navController
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Card Dijemput
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF0FF)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_penjemputan),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dijemput",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Petugas datang ke lokasi",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Button(
                        onClick = { navController.popBackStack() },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        modifier = Modifier.border(1.5.dp, Color(0xFF0A2B6E), RoundedCornerShape(50))
                    ) {
                        Text(
                            text = "Ubah",
                            color = Color(0xFF0A2B6E),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Label "Dijemput dari"
            Text(
                text = "Dijemput dari",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card Lokasi dengan garis penghubung
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        // Lokasi Tujuan (Bank Sampah)
                        Row(verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(Color(0xFFE8EAF6), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_home2),
                                    contentDescription = "Bank Sampah",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Bandar Khalipah",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(0xFF0A2B6E)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Desa Bandar Khalipah, Kecamatan Percut Sei Tuan, Kabupaten Deli Serdang",
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    lineHeight = 20.sp
                                )
                            }
                        }

                        // Garis Divider dengan design
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = Color(0xFFE0E0E0)
                            )
                        }

                        // Lokasi User (Lokasi Saat Ini)
                        Row(verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(Color(0xFFE3F2FD), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_location),
                                    contentDescription = "Lokasi Anda",
                                    tint = Color(0xFF1976D2),
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Lokasi Anda saat ini",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(0xFF0A2B6E)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = userAddress,
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detail Setoran Sampah
            Text(
                text = "Detail Setoran Sampah",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card Detail (sama seperti PengantaranScreen)
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "ID Laporan",
                            fontSize = 14.sp,
                            color = Color(0xFF5B9FFF),
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = pickupDetail.idLaporan,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Nama Petugas",
                                    fontSize = 14.sp,
                                    color = Color(0xFF5B9FFF),
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = pickupDetail.petugas,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Tanggal & Waktu",
                                    fontSize = 14.sp,
                                    color = Color(0xFF5B9FFF),
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    text = pickupDetail.tanggalWaktu,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        pickupDetail.listSampah.forEach { (nama, berat, harga) ->
                            val iconRes = when {
                                nama.contains("Plastik", ignoreCase = true) -> R.drawable.botol
                                nama.contains("Kardus", ignoreCase = true) -> R.drawable.kardus
                                nama.contains("Kertas", ignoreCase = true) -> R.drawable.kertas
                                else -> R.drawable.botol
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = nama,
                                        fontSize = 16.sp,
                                        color = Color(0xFF5B9FFF),
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Text(
                                    text = "$berat kg × Rp ${"%,d".format(harga)}",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = Color(0xFFE0E0E0)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        val totalBerat = pickupDetail.listSampah.sumOf { it.second }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total Sampah Masuk",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "$totalBerat kg",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Button(
                    onClick = { /* aksi request penjemputan */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A2B6E))
                ) {
                    Text(
                        text = "Minta Dijemput",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreens() {
    val navController = rememberNavController()
    val sampleData = PickupDetail(
        idLaporan = "XXXXXX",
        petugas = "Lionel Messi",
        tanggalWaktu = "15 Mei 2025, 10.30",
        listSampah = listOf(
            Triple("Botol Plastik", 20.5, 1000),
            Triple("Kardus", 15.0, 2000),
            Triple("Kertas", 10.5, 1500)
        )
    )

    // Preview PenjemputanScreen
    PenjemputanScreen(
        navController = navController,
        pickupDetail = sampleData,
        userAddress = "GJQ4+RGP, Kabupaten Deli Serdang"
    )
}