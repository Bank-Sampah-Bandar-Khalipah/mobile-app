package com.example.nasabahcompose.ui.nasabah.screens.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PickupDeliverySection(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Penjemputan sekarang untuk ambil di tempat atau diantar ke lokasimu!",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Pick Up Card
            Card(
                onClick = {
                    navController.navigate("delivery")
                },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(160.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Antar Sendiri", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF0D1541))
                        Text("Antar sampah ke lokasi", fontSize = 14.sp, color = Color(0xFF3B3B3B))
                    }

                    // Bubble dekorasi kiri bawah (dummy circle)
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 10.dp, y = 10.dp)
                            .background(Color(0xFF47527B), shape = RoundedCornerShape(50))
                    )
                }
            }

            // Delivery Card
            Card(
                onClick = {
                    navController.navigate("pickup")
                },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(160.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("Dijemput", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF1A4AC3))
                        Text("Segera diantar ke lokasimu", fontSize = 14.sp, color = Color(0xFF3B3B3B))
                    }

                    // Bubble dekorasi kanan bawah (dummy circle)
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 10.dp, y = 10.dp)
                            .background(Color(0xFF7392E1), shape = RoundedCornerShape(50))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PickupDeliverySectionPreview() {
    // Dummy nav controller
    val dummyNavController = rememberNavController()
    PickupDeliverySection(navController = dummyNavController)
}

