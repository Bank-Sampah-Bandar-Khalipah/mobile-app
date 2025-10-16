package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.nasabahcompose.ui.nasabah.screens.components.CommonHeader

@Composable
fun TrashPriceScreen(
    navController: NavController,
    trashList: List<Trash>
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF4F2F2))
        ) {
            // Header menggunakan CommonHeader
            CommonHeader(
                title = "Harga Sampah",
                navController = navController
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Search bar
                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Cari Sampah") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }

                // Grid List harga sampah (filter sesuai search)
                items(trashList.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                            it.category.contains(searchQuery, ignoreCase = true)
                }) { trash ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        Column {
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
}

@Preview(showBackground = true)
@Composable
fun PreviewTrashPriceScreen() {
    val dummyList = listOf(
        Trash("Tutup Botol", 2000, R.drawable.kardus2, "Plastik"),
        Trash("Kardus", 1500, R.drawable.kardus2, "Kardus"),
        Trash("Kertas Buku", 1000, R.drawable.buku, "Kertas")
    )
    TrashPriceScreen(trashList = dummyList, navController = rememberNavController())
}