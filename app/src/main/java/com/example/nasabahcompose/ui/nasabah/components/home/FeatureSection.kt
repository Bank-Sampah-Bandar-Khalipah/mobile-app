package com.example.nasabahcompose.ui.nasabah.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.nasabahcompose.R

data class FeatureItem(val title: String, val iconRes: Int)

@Composable
fun FeatureSection(navController: NavHostController) {
    val features = listOf(
        FeatureItem("Pencairan Dana", R.drawable.ic_money),
        FeatureItem("Informasi Sampah", R.drawable.ic_info),
        FeatureItem("Slip Setoran Sampah", R.drawable.ic_slip),
        FeatureItem("Informasi Tempat", R.drawable.ic_location),
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Fitur-fitur", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(features) { feature ->
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(140.dp)
                        .clickable {
                            if (feature.title == "Informasi Tempat") {
                                navController.navigate("location")
                            } else if (feature.title == "Pencairan Dana") {
                                navController.navigate("history")
                            } else if (feature.title == "Informasi Sampah") {
                                navController.navigate("trash_price")
                            } else if (feature.title == "Slip Setoran Sampah") {
                                navController.navigate("slip_setoran")
                            }
                        },
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F0FF)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            modifier = Modifier.size(48.dp),
                            shape = RoundedCornerShape(50),
                            color = Color.White
                        ) {
                            Icon(
                                painter = painterResource(id = feature.iconRes),
                                contentDescription = feature.title,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                tint = Color(0xFF1A4AC3)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = feature.title,
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
