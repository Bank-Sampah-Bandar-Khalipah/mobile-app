package com.example.nasabahcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasabahcompose.R
import com.example.nasabahcompose.data.Trash
import com.example.nasabahcompose.ui.theme.NasabahComposeTheme

@Composable
fun TrashPriceSection(trashList: List<Trash>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Harga Sampah", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                "Lihat Semua",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(trashList) { trash ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(140.dp)
                        .height(180.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Gambar item
                        Image(
                            painter = painterResource(id = trash.imageRes),
                            contentDescription = trash.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = trash.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,

                        )

                        Text(
                            text = trash.category,
                            fontSize = 12.sp,
                            color = Color.Gray,
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
        Trash("Tutup Botol", 500, R.drawable.sampah, "Plastik"),
        Trash("Kertas Buku", 1000, R.drawable.sampah, "Kertas"),
        Trash("Kardus", 1500, R.drawable.sampah, "Kardus")
    )

    NasabahComposeTheme {
        TrashPriceSection(trashList = dummyData)
    }
}

