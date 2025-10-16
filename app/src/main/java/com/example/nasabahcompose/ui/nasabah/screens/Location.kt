package com.example.nasabahcompose.ui.nasabah.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nasabahcompose.R
import com.example.nasabahcompose.ui.nasabah.screens.components.CommonHeader
import com.example.nasabahcompose.ui.nasabah.screens.components.MyNavbarBar

@Composable
fun LocationScreen(navController: NavController, username: String) {
    val context = LocalContext.current

    val latitude = 3.6145786
    val longitude = 98.7432867
    val urlMaps = "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"

    Scaffold(
        bottomBar = { MyNavbarBar(navController = navController, username = username) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
        ) {
            // Header menggunakan CommonHeader
            CommonHeader(
                title = "Informasi Tempat",
                navController = navController
            )

            // Card dengan gambar dan deskripsi bawah
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlMaps))
                            context.startActivity(intent)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.loc_banksampah),
                            contentDescription = "Peta Lokasi",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )

                        // Kotak putih untuk teks di bawah gambar
                        Box(
                            modifier = Modifier
                                .align(androidx.compose.ui.Alignment.BottomCenter)
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Desa Bandar Khalipah, Kecamatan Percut Sei Tuan,\nKabupaten Deli Serdang",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationScreen() {
    val navController = rememberNavController()
    LocationScreen(navController = navController, username = "PreviewUser")
}