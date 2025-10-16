package com.example.nasabahcompose.ui.nasabah.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.theme.NasabahComposeTheme

@Composable
fun TrashPriceSection(navController: NavController,
                      trashList: List<Trash>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Harga Sampah", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp))

            Text(
                "Lihat Semua",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        navController.navigate("trash_price")
                    }
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(trashList) { trash ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(140.dp)
                        .height(180.dp) ,
                    colors = CardDefaults.cardColors(containerColor = Color.White)

                ) {
                    Column(
                    ) {
                        // Gambar item
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(trash.imageRes) // bisa drawable atau url
                                .crossfade(true)
                                .build(),
                            contentDescription = trash.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = trash.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "Rp ${trash.price}/kg",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF204A31),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = trash.category,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrashPriceSectionPreview() {
    val dummyData = listOf(
        Trash("Plastik", 500, R.drawable.sampah, "Plastik"),
        Trash("Kertas Buku", 1000, R.drawable.buku, "Kertas"),
        Trash("Kardus", 1500, R.drawable.kardus2, "Kardus")
    )

    NasabahComposeTheme {
        TrashPriceSection(navController = rememberNavController(), trashList = dummyData)
    }
}

