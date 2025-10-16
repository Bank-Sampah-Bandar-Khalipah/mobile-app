package com.example.nasabahcompose.ui.nasabah.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
fun SetoranListScreen(navController: NavController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F2F2))
        ) {
            // Header menggunakan CommonHeader
            CommonHeader(
                title = "Slip Setoran Sampah",
                navController = navController
            )

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // List Card (pakai dummySetoranList kamu)
                items(dummySetoranList) { setoran ->
                    SetoranCard(
                        setoran = setoran,
                        onClick = {
                            navController.navigate("detailSetoran/${setoran.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SetoranCard(
    setoran: Setoran,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon kiri (kotak kecil dengan ic_cart)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Icon Setoran",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info tengah: tanggal & ID
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = setoran.tanggal,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = setoran.id,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Poin kanan + panah
            Text(
                text = "+${setoran.totalPoin} PTS",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowright),
                contentDescription = "Detail",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(16.dp)
            )
        }
    }
}

/**
 * Preview: memakai dummySetoranList yang sama persis.
 * Tidak mengubah desain atau data.
 */
@Preview(showBackground = true)
@Composable
private fun SetoranListScreenPreview() {
    val navController = rememberNavController()
    SetoranListScreen(navController = navController)
}